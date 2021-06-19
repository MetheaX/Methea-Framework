package io.github.metheax.api.service;

import io.github.metheax.api.config.security.SecurityConstants;
import io.github.metheax.api.domain.RefreshTokenPayload;
import io.github.metheax.api.domain.RequestTokenPayload;
import io.github.metheax.api.domain.RevokeTokenPayload;
import io.github.metheax.api.domain.Token;
import io.github.metheax.config.security.MetheaPrincipal;
import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.view.GroupAuthorityView;
import io.github.metheax.domain.entity.TSessionManagement;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.domain.entity.TClient;
import io.github.metheax.domain.entity.TSystemCertificate;
import io.github.metheax.exception.CertificateNotFoundException;
import io.github.metheax.exception.InvalidClientSecretException;
import io.github.metheax.repository.SessionManagementRepository;
import io.github.metheax.repository.PermissionRepository;
import io.github.metheax.repository.UserRepository;
import io.github.metheax.repository.ClientRepository;
import io.github.metheax.repository.SystemCertificateRepository;
import io.github.metheax.utils.SystemUtils;
import io.github.metheax.utils.auth.JwtUtil;
import io.github.metheax.utils.auth.MetheaPasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MetheaAuthenticationService implements UserDetailsService, AuthenticationService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PermissionRepository permissionRepository;
    private final SystemCertificateRepository certificateRepository;
    private final SessionManagementRepository sessionManagementRepository;
    private final MetheaPasswordEncoder encoder;

    private static final String NO_CERTIFICATE_MSG = "No active certificate could be found! Please check system certificate!";

    @Inject
    public MetheaAuthenticationService(UserRepository userRepository,
                                       ClientRepository clientRepository, PermissionRepository permissionRepository,
                                       SystemCertificateRepository certificateRepository,
                                       SessionManagementRepository sessionManagementRepository, MetheaPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.permissionRepository = permissionRepository;
        this.certificateRepository = certificateRepository;
        this.sessionManagementRepository = sessionManagementRepository;
        this.encoder = encoder;
    }

    @Override
    public Token generateTokenFromUser(RequestTokenPayload payload, HttpServletRequest request) {
        PrincipalAuthentication authentication = loadUserAsClient(payload);
        if (ObjectUtils.isEmpty(authentication)) {
            authentication = loadClient(payload);
            if (ObjectUtils.isEmpty(authentication)) {
                return null;
            }
        }
        return generateToken(authentication, request, false);
    }

    @Override
    public Token generateTokenFromRefreshToken(RefreshTokenPayload payload, HttpServletRequest request) {
        TSystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MetheaConstant.CERT_TYPE_2,
                MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException(NO_CERTIFICATE_MSG);
        }
        String subject = JwtUtil.decodeToken(payload.getRefreshToken(), certificate.getPrivateKey());
        if (StringUtils.isEmpty(subject)) {
            return null;
        }
        if (validateUserRevokedToken(subject)) {
            return null;
        }
        TUser user = userRepository.findByUsernameAndStatus(subject.split(MetheaConstant.COLON)[0], MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return generateToken(buildPrincipal(user), request, true);
    }

    @Override
    public void revokeAccessToken(RevokeTokenPayload payload, HttpServletRequest request) {
        TSystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MetheaConstant.CERT_TYPE,
                MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException(NO_CERTIFICATE_MSG);
        }
        String subject = JwtUtil.decodeToken(payload.getClientToken(), certificate.getPrivateKey());
        String[] arr = subject.split(MetheaConstant.COLON);
        if (StringUtils.isNotEmpty(payload.getClientID()) && !payload.getClientID().equals(arr[0])) {
            throw new InvalidClientSecretException("Provided client secret invalid!");
        }

        List<TSessionManagement> sessionManagements = sessionManagementRepository
                .findAllByUserLoginIdAndSessionIdAndIsLogout(arr[0], arr[1], false);

        if (!CollectionUtils.isEmpty(sessionManagements)) {
            for (TSessionManagement o : sessionManagements) {
                o.setLogout(true);
                sessionManagementRepository.save(o);
            }
        }
    }

    @Override
    public boolean validateUserRevokedToken(String subject) {
        String[] arr = subject.split(MetheaConstant.COLON);
        List<TSessionManagement> sessionManagements = sessionManagementRepository
                .findAllByUserLoginIdAndSessionIdAndIsLogout(arr[0], arr[1], false);
        return CollectionUtils.isEmpty(sessionManagements);
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsernameAndStatus(s, MetheaConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            TClient client = clientRepository.findClientByClientIdAndStatus(s, MetheaConstant.ACTIVE_STATUS);
            if (ObjectUtils.isEmpty(client)) {
                return null;
            }
            return buildPrincipal(client);
        }
        return buildPrincipal(user);
    }

    private Token generateToken(PrincipalAuthentication authentication, HttpServletRequest req, boolean isRefreshToken) {
        Token token = null;
        TSystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MetheaConstant.CERT_TYPE,
                MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException(NO_CERTIFICATE_MSG);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(SecurityConstants.EXPIRATION_ACCESS_TOKEN_TIME));
        Map<String, String> map = JwtUtil.encodeToken(certificate.getPublicKey(), authentication.getUsername(), SystemUtils.getBaseUrl(req), cal);
        if (StringUtils.isNotEmpty(map.get(MetheaConstant.JWT_TOKEN))) {
            token = new Token();
            if (!isRefreshToken) {
                token.setRefreshToken(generateRefreshToken(authentication.getUsername(), req));
            }
            token.setAccessToken(map.get(MetheaConstant.JWT_TOKEN));
            token.setExpiredIn(String.valueOf(cal.getTimeInMillis()));
            token.setTokenType(SecurityConstants.TOKEN_PREFIX);
        }
        TSessionManagement sessionManagement = new TSessionManagement();
        sessionManagement.setId(UUID.randomUUID().toString());
        sessionManagement.setLogout(false);
        sessionManagement.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
        sessionManagement.setUserLoginId(authentication.getUsername());
        sessionManagement.setStatus(MetheaConstant.ACTIVE_STATUS);

        sessionManagementRepository.save(sessionManagement);

        return token;
    }

    private String generateRefreshToken(String username, HttpServletRequest req) {
        TSystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MetheaConstant.CERT_TYPE_2,
                MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException(NO_CERTIFICATE_MSG);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(SecurityConstants.EXPIRATION_REF_TOKEN_TIME));
        Map<String, String> map = JwtUtil.encodeToken(certificate.getPublicKey(),
                username, SystemUtils.getBaseUrl(req), cal);
        if (StringUtils.isNotEmpty(map.get(MetheaConstant.JWT_TOKEN))) {
            return map.get(MetheaConstant.JWT_TOKEN);
        }
        return StringUtils.EMPTY;
    }

    private PrincipalAuthentication loadUserAsClient(RequestTokenPayload payload) {
        TUser user = userRepository.findByUsernameAndStatus(payload.getClientId(), MetheaConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        if (!encoder.matches(payload.getClientSecret(), user.getPassword())) {
            return null;
        }
        return buildPrincipal(user);
    }

    private PrincipalAuthentication loadClient(RequestTokenPayload payload) {
        TClient client = clientRepository.findClientByClientIdAndStatus(payload.getClientId(), MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(client)) {
            return null;
        }
        if (!encoder.matches(payload.getClientSecret(), client.getClientSecret())) {
            return null;
        }
        return buildPrincipal(client);
    }

    private PrincipalAuthentication buildPrincipal(TClient client) {
        MetheaPrincipal principal = new MetheaPrincipal();
        Map<String, Object> param = new HashMap<>();
        param.put("username", client.getClientId());
        principal.setUsername(client.getClientId());
        principal.setForceUserResetPassword(MetheaConstant.NO);
        return new PrincipalAuthentication(client.getClientId(), client.getClientSecret(), new ArrayList<>(),
                permissionRepository.getByQuery(param, PermissionView.class), principal);
    }

    private PrincipalAuthentication buildPrincipal(TUser user) {
        MetheaPrincipal principal = new MetheaPrincipal();
        List<GroupAuthorityView> grantedAuthority = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("roles", user.getRoles());
        BeanUtils.copyProperties(user, principal);
        GroupAuthorityView groupAuthorityView = new GroupAuthorityView(user.getGroup().getGroupCode());
        grantedAuthority.add(groupAuthorityView);
        return new PrincipalAuthentication(user.getUsername(), user.getPassword(), grantedAuthority,
                permissionRepository.getByQuery(param, PermissionView.class), principal);
    }
}
