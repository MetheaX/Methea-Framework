package io.github.metheax.api.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.metheax.api.service.MetheaAuthenticationService;
import io.github.metheax.config.security.GrantedPermission;
import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.entity.PublicPermission;
import io.github.metheax.repository.WhiteURIPermissionRepository;
import io.github.metheax.service.KeyStoreService;
import io.github.metheax.utils.auth.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author : Kuylim Tith
 * Date : 09/04/2018
 */
public class WebServiceAuthorizationFilter extends BasicAuthenticationFilter {

    private final MetheaAuthenticationService metheaAuthenticationService;
    private final KeyStoreService keyStoreService;
    private final WhiteURIPermissionRepository whiteURLRepository;
    private static final String WHITE_URL = "auth";

    @Inject
    public WebServiceAuthorizationFilter(MetheaAuthenticationService metheaAuthenticationService,
                                         AuthenticationManager authenticationManager, KeyStoreService keyStoreService,
                                         WhiteURIPermissionRepository whiteURLRepository) {
        super(authenticationManager);
        this.keyStoreService = keyStoreService;
        this.metheaAuthenticationService = metheaAuthenticationService;
        this.whiteURLRepository = whiteURLRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        boolean isNotAuthorize = true;
        PrincipalAuthentication authentication = null;
        String token = req.getHeader(SecurityConstants.HEADER_STRING);
        List<PublicPermission> whiteURIs = whiteURLRepository.findAllByStatus(MetheaConstant.ACTIVE_STATUS);
        PublicPermission tmp = null;
        // Initial white list
        if (CollectionUtils.isNotEmpty(whiteURIs)) {
            Map<String, PublicPermission> map = whiteURIs.stream()
                    .collect(Collectors.toMap(x -> x.getResource().getResourceName(), o -> o));
            // check white parent config
            String wURI = StringUtils.EMPTY;
            for (String str : req.getRequestURI().split(MetheaConstant.SLASH)) {
                wURI = wURI.concat(MetheaConstant.SLASH).concat(StringUtils.stripToEmpty(str));
                StringBuilder builder = new StringBuilder(wURI);
                String key = (builder.deleteCharAt(0) + MetheaConstant.SLASH_STAR);
                if (map.containsKey(key)) {
                    tmp = map.get(key);
                    break;
                }
            }
            // check exact white uri
            if (map.containsKey(req.getRequestURI()) && ObjectUtils.isEmpty(tmp)) {
                tmp = map.get(req.getRequestURI());
            } else if (map.containsKey(req.getRequestURI() + MetheaConstant.SLASH_STAR) && ObjectUtils.isEmpty(tmp)) {
                tmp = map.get(req.getRequestURI() + MetheaConstant.SLASH_STAR);
            } else if (map.containsKey(req.getRequestURI() + MetheaConstant.DOUBLE_STAR) && ObjectUtils.isEmpty(tmp)) {
                tmp = map.get(req.getRequestURI() + MetheaConstant.DOUBLE_STAR);
            }
        }

        // validate allow method
        if (!ObjectUtils.isEmpty(tmp)) {
            if (tmp.getAllowedMethod().contains(req.getMethod())) {
                authentication = metheaAuthenticationService.loadUserByUsername(MetheaConstant.PUBLIC_USER);
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(authentication, null, authentication.getAuthorities()));
                isNotAuthorize = false;
            }
        }

        // validate token
        if (!StringUtils.isEmpty(token) && !WHITE_URL.equals(req.getRequestURI().split(MetheaConstant.SLASH)[1]) && isNotAuthorize) {

            String subject = JwtUtil.decodeToken(token.replace(SecurityConstants.TOKEN_PREFIX, StringUtils.EMPTY),
                    keyStoreService.loadTokenKeyPair().getPrivate());
            if (!StringUtils.isEmpty(subject)) {
                authentication = metheaAuthenticationService.loadUserByUsername(subject.split(MetheaConstant.COLON)[0]);
                if (metheaAuthenticationService.validateUserRevokedToken(subject)) {
                    constructUnAuthorizeResponse(res);
                    return;
                }
            } else {
                constructUnAuthorizeResponse(res);
                return;
            }
        }
        // validate permission
        if (!ObjectUtils.isEmpty(authentication) && isNotAuthorize) {
            String uri = StringUtils.EMPTY;
            String requestURI = req.getRequestURI();
            List<GrantedPermission> grantedPermissions = authentication.getGrantedPermissions();
            List<String> grantedURIs = CollectionUtils.emptyIfNull(grantedPermissions).stream()
                    .map(GrantedPermission::getGrantedPermission)
                    .collect(Collectors.toList());

            //>>>>> is it contain parent configuration?
            for (String str : requestURI.split(MetheaConstant.SLASH)) {
                uri = uri.concat(MetheaConstant.SLASH).concat(StringUtils.stripToEmpty(str));
                StringBuilder builder = new StringBuilder(uri);
                if (grantedURIs.contains(requestURI) || grantedURIs.contains((builder.deleteCharAt(0) + MetheaConstant.SLASH_STAR))) {
                    isNotAuthorize = false;
                }
            }
            //>>>>> is it config with this specific URI, will check on second condition when request "/"
            if (grantedURIs.contains(requestURI) || grantedURIs.contains(requestURI + MetheaConstant.SLASH_STAR)
                    || grantedURIs.contains(requestURI + MetheaConstant.DOUBLE_STAR)) {
                isNotAuthorize = false;
            }
            if (isNotAuthorize) {
                constructUnAuthorizeResponse(res);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(authentication, null, authentication.getAuthorities()));
        }
        chain.doFilter(req, res);
    }

    private void constructUnAuthorizeResponse(HttpServletResponse res) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Unauthorized Access!!");
        map.put("status", 401);

        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        String jsonFormat = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setContentLength(jsonFormat.length());
        res.getWriter().write(jsonFormat);
    }
}
