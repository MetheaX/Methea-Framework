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
import io.github.metheax.domain.entity.SessionManagement;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.domain.entity.User;
import io.github.metheax.exception.InvalidClientSecretException;
import io.github.metheax.repository.SessionManagementRepository;
import io.github.metheax.repository.PermissionRepository;
import io.github.metheax.repository.UserRepository;
import io.github.metheax.service.KeyStoreService;
import io.github.metheax.utils.SystemUtils;
import io.github.metheax.utils.auth.JwtUtil;
import io.github.metheax.utils.auth.MetheaPasswordEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

import java.util.*;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
@Service
@Transactional
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MetheaAuthenticationService implements UserDetailsService, AuthenticationService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final SessionManagementRepository sessionManagementRepository;
    private final MetheaPasswordEncoder encoder;
    private final KeyStoreService keyStoreService;

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
        String subject = JwtUtil.decodeToken(payload.getRefreshToken(), keyStoreService.loadRefreshTokenKeyPair().getPrivate());
        if (StringUtils.isEmpty(subject)) {
            return null;
        }
        if (validateUserRevokedToken(subject)) {
            return null;
        }
        User user = userRepository.findByUsernameAndStatus(subject.split(MetheaConstant.COLON)[0], MetheaConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return generateToken(buildPrincipal(user), request, true);
    }

    @Override
    public void revokeAccessToken(RevokeTokenPayload payload, HttpServletRequest request) {
        String subject = JwtUtil.decodeToken(payload.getToken(), keyStoreService.loadTokenKeyPair().getPrivate());
        String[] arr = subject.split(MetheaConstant.COLON);
        if (StringUtils.isNotEmpty(payload.getUsername()) && !payload.getUsername().equals(arr[0])) {
            throw new InvalidClientSecretException("Provided client secret invalid!");
        }

        List<SessionManagement> sessionManagements = sessionManagementRepository
                .findAllByUserLoginIdAndSessionIdAndIsLogout(arr[0], arr[1], false);

        if (!CollectionUtils.isEmpty(sessionManagements)) {
            for (SessionManagement o : sessionManagements) {
                o.setLogout(true);
                sessionManagementRepository.save(o);
            }
        }
    }

    @Override
    public boolean validateUserRevokedToken(String subject) {
        String[] arr = subject.split(MetheaConstant.COLON);
        List<SessionManagement> sessionManagements = sessionManagementRepository
                .findAllByUserLoginIdAndSessionIdAndIsLogout(arr[0], arr[1], false);
        return CollectionUtils.isEmpty(sessionManagements);
    }

    @Override
    public PrincipalAuthentication loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndStatus(s, MetheaConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        return buildPrincipal(user);
    }

    private Token generateToken(PrincipalAuthentication authentication, HttpServletRequest req, boolean isRefreshToken) {
        Token token = null;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(SecurityConstants.EXPIRATION_ACCESS_TOKEN_TIME));

        Map<String, String> map = JwtUtil.encodeToken(keyStoreService.loadTokenKeyPair().getPublic(),
                authentication.getUsername(), SystemUtils.getBaseUrl(req), cal);
        if (StringUtils.isNotEmpty(map.get(MetheaConstant.JWT_TOKEN))) {
            token = new Token();
            if (!isRefreshToken) {
                token.setRefreshToken(generateRefreshToken(authentication.getUsername(), req));
            }
            token.setAccessToken(map.get(MetheaConstant.JWT_TOKEN));
            token.setExpiredIn(String.valueOf(cal.getTimeInMillis()));
            token.setTokenType(SecurityConstants.TOKEN_PREFIX);
        }
        SessionManagement sessionManagement = new SessionManagement();
        sessionManagement.setLogout(false);
        sessionManagement.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
        sessionManagement.setUserLoginId(authentication.getUsername());
        sessionManagement.setStatus(MetheaConstant.ACTIVE_STATUS);

        sessionManagementRepository.save(sessionManagement);

        return token;
    }

    private String generateRefreshToken(String username, HttpServletRequest req) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + Long.parseLong(SecurityConstants.EXPIRATION_REF_TOKEN_TIME));
        Map<String, String> map = JwtUtil.encodeToken(keyStoreService.loadRefreshTokenKeyPair().getPublic(),
                username, SystemUtils.getBaseUrl(req), cal);
        if (StringUtils.isNotEmpty(map.get(MetheaConstant.JWT_TOKEN))) {
            return map.get(MetheaConstant.JWT_TOKEN);
        }
        return StringUtils.EMPTY;
    }

    private PrincipalAuthentication loadUserAsClient(RequestTokenPayload payload) {
        User user = userRepository.findByUsernameAndStatus(payload.getUsername(), MetheaConstant.ACTIVE_STATUS);

        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        if (!encoder.matches(payload.getPassword(), user.getPassword())) {
            return null;
        }
        return buildPrincipal(user);
    }

    private PrincipalAuthentication buildPrincipal(User user) {
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
