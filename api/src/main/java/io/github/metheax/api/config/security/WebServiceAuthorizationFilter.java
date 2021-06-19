package io.github.metheax.api.config.security;

import io.github.metheax.api.service.MetheaAuthenticationService;
import io.github.metheax.config.security.GrantedPermission;
import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.entity.TPublicPermission;
import io.github.metheax.domain.entity.TSystemCertificate;
import io.github.metheax.exception.CertificateNotFoundException;
import io.github.metheax.repository.WhiteURIPermissionRepository;
import io.github.metheax.repository.SystemCertificateRepository;
import io.github.metheax.utils.SystemUtils;
import io.github.metheax.utils.auth.JwtUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author : Kuylim Tith
 * Date : 09/04/2018
 */
public class WebServiceAuthorizationFilter extends BasicAuthenticationFilter {

    private final MetheaAuthenticationService metheaAuthenticationService;
    private final SystemCertificateRepository certificateRepository;
    private final WhiteURIPermissionRepository whiteURLRepository;
    private static final String UNAUTHORIZED_ACCESS_URL = "/unauthorized-access";
    private static final String WHITE_URL = "auth";

    @Inject
    public WebServiceAuthorizationFilter(MetheaAuthenticationService metheaAuthenticationService,
                                         AuthenticationManager authenticationManager,
                                         SystemCertificateRepository certificateRepository,
                                         WhiteURIPermissionRepository whiteURLRepository) {
        super(authenticationManager);
        this.certificateRepository = certificateRepository;
        this.metheaAuthenticationService = metheaAuthenticationService;
        this.whiteURLRepository = whiteURLRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        boolean isNotAuthorize = true;
        PrincipalAuthentication authentication = null;
        String token = req.getHeader(SecurityConstants.HEADER_STRING);
        List<TPublicPermission> whiteURIs = whiteURLRepository.findAllByStatus(MetheaConstant.ACTIVE_STATUS);
        TPublicPermission tmp = null;
        // Initial white list
        if (CollectionUtils.isNotEmpty(whiteURIs)) {
            Map<String, TPublicPermission> map = whiteURIs.stream()
                    .collect(Collectors.toMap(x -> x.getResource().getResourceName(), o -> o));
            // check white parent config
            String wURI = StringUtils.EMPTY;
            for (String str : req.getRequestURI().split(MetheaConstant.SLASH)) {
                wURI = wURI.concat(MetheaConstant.SLASH).concat(StringUtils.stripToEmpty(str));
                StringBuilder builder = new StringBuilder(wURI);
                String key = (builder.deleteCharAt(0).toString() + MetheaConstant.SLASH_STAR);
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
            TSystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MetheaConstant.CERT_TYPE,
                    MetheaConstant.ACTIVE_STATUS);
            if (ObjectUtils.isEmpty(certificate)) {
                throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
            }
            String subject = JwtUtil.decodeToken(token.replace(SecurityConstants.TOKEN_PREFIX, StringUtils.EMPTY), certificate.getPrivateKey());
            if (!StringUtils.isEmpty(subject)) {
                authentication = metheaAuthenticationService.loadUserByUsername(subject.split(MetheaConstant.COLON)[0]);
                if (metheaAuthenticationService.validateUserRevokedToken(subject)) {
                    res.sendRedirect(SystemUtils.getBaseUrl(req).concat(UNAUTHORIZED_ACCESS_URL));
                    return;
                }
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
                if (grantedURIs.contains(requestURI) || grantedURIs.contains((builder.deleteCharAt(0).toString() + MetheaConstant.SLASH_STAR))) {
                    isNotAuthorize = false;
                }
            }
            //>>>>> is it config with this specific URI, will check on second condition when request "/"
            if (grantedURIs.contains(requestURI) || grantedURIs.contains(requestURI + MetheaConstant.SLASH_STAR)
                    || grantedURIs.contains(requestURI + MetheaConstant.DOUBLE_STAR)) {
                isNotAuthorize = false;
            }
            if (isNotAuthorize) {
                res.sendRedirect(SystemUtils.getBaseUrl(req).concat(UNAUTHORIZED_ACCESS_URL));
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(authentication, null, authentication.getAuthorities()));
        }
        chain.doFilter(req, res);
    }
}
