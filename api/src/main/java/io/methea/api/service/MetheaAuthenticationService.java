package io.methea.api.service;

import io.methea.api.config.security.SecurityConstants;
import io.methea.api.domain.RefreshTokenPayload;
import io.methea.api.domain.RequestTokenPayload;
import io.methea.api.domain.Token;
import io.methea.config.security.MetheaPrincipal;
import io.methea.config.security.PrincipalAuthentication;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.group.view.GroupAuthorityView;
import io.methea.domain.configuration.permission.view.PermissionView;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.domain.webservice.system.entity.SystemCertificate;
import io.methea.exception.CertificateNotFoundException;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.configuration.permission.UserGrantedPermissionRepository;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import io.methea.utils.SystemUtils;
import io.methea.utils.auth.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 08/08/2020
 */
@Service
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MetheaAuthenticationService implements UserDetailsService, AuthenticationService {

    private final UserRepository userRepository;
    private final UserGrantedPermissionRepository userGrantedPermissionRepository;
    private final UserGroupRepository userGroupRepository;
    private final SystemCertificateRepository certificateRepository;

    @Inject
    public MetheaAuthenticationService(UserRepository userRepository,
                                       UserGrantedPermissionRepository userGrantedPermissionRepository,
                                       UserGroupRepository userGroupRepository,
                                       SystemCertificateRepository certificateRepository) {
        this.userRepository = userRepository;
        this.userGrantedPermissionRepository = userGrantedPermissionRepository;
        this.userGroupRepository = userGroupRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public Token generateTokenFromUser(RequestTokenPayload payload, HttpServletRequest request) {
        PrincipalAuthentication authentication = loadUserAsClient(payload);
        if (ObjectUtils.isEmpty(authentication)) {
            return null;
        }
        return generateToken(authentication, request, false);
    }

    @Override
    public Token generateTokenFromRefreshToken(RefreshTokenPayload payload, HttpServletRequest request) {
        SystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MConstant.CERT_TYPE_2,
                MConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
        }
        String username = JwtUtil.decodeToken(payload.getRefreshToken(), certificate.getPrivateKey());
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        TUser user = userRepository.findByUsernameAndStatus(username, MConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return generateToken(buildPrincipal(user), request, true);
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = userRepository.findByUsernameAndStatus(s, MConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return buildPrincipal(user);
    }

    private Token generateToken(PrincipalAuthentication authentication, HttpServletRequest req, boolean isRefreshToken) {
        Token token = null;
        SystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MConstant.CERT_TYPE,
                MConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(SecurityConstants.EXPIRATION_ACCESS_TOKEN_TIME));
        Map<String, String> map = JwtUtil.encodeToken(certificate.getPublicKey(), authentication.getUsername(), SystemUtils.getBaseUrl(req), cal);
        if (StringUtils.isNotEmpty(map.get(MConstant.JWT_TOKEN))) {
            token = new Token();
            if (!isRefreshToken) {
                token.setRefreshToken(generateRefreshToken(authentication.getUsername(), req));
            }
            token.setAccessToken(map.get(MConstant.JWT_TOKEN));
            token.setExpiredIn(String.valueOf(cal.getTimeInMillis()));
            token.setTokenType(SecurityConstants.TOKEN_PREFIX);
        }
        return token;
    }

    private String generateRefreshToken(String username, HttpServletRequest req) {
        SystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MConstant.CERT_TYPE_2,
                MConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(SecurityConstants.EXPIRATION_REF_TOKEN_TIME));
        Map<String, String> map = JwtUtil.encodeToken(certificate.getPublicKey(),
                username, SystemUtils.getBaseUrl(req), cal);
        if (StringUtils.isNotEmpty(map.get(MConstant.JWT_TOKEN))) {
            return map.get(MConstant.JWT_TOKEN);
        }
        return StringUtils.EMPTY;
    }

    private PrincipalAuthentication loadUserAsClient(RequestTokenPayload payload) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        TUser user = userRepository.findByUsernameAndStatus(payload.getClientId(), MConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        if (!encoder.matches(payload.getClientSecret(), user.getPassword())) {
            return null;
        }
        return buildPrincipal(user);
    }

    private PrincipalAuthentication buildPrincipal(TUser user) {
        MetheaPrincipal principal = new MetheaPrincipal();
        Map<String, Object> param = new HashMap<>();
        param.put("username", user.getUsername());
        BeanUtils.copyProperties(user, principal);
        return new PrincipalAuthentication(user.getUsername(), user.getPassword(),
                userGroupRepository.getByQuery(param, GroupAuthorityView.class),
                userGrantedPermissionRepository.getByQuery(param, PermissionView.class), principal);
    }

}
