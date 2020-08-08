package io.methea.api.config.security;

import io.methea.api.service.MetheaAuthenticationService;
import io.methea.config.security.GrantedPermission;
import io.methea.config.security.PrincipalAuthentication;
import io.methea.constant.MConstant;
import io.methea.domain.webservice.system.entity.SystemCertificate;
import io.methea.exception.CertificateNotFoundException;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import io.methea.utils.SystemUtils;
import io.methea.utils.auth.JwtUtil;
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
import java.util.stream.Collectors;

/**
 * Author : DKSilverX
 * Date : 09/04/2018
 */
public class WebServiceAuthorizationFilter extends BasicAuthenticationFilter {

    private final MetheaAuthenticationService metheaAuthenticationService;
    private final SystemCertificateRepository certificateRepository;
    private static final String UNAUTHORIZED_ACCESS_URL = "/unauthorized-access";
    private static final String WHITE_URL = "auth";

    @Inject
    public WebServiceAuthorizationFilter(MetheaAuthenticationService metheaAuthenticationService ,
                                         AuthenticationManager authenticationManager,
                                         SystemCertificateRepository certificateRepository) {
        super(authenticationManager);
        this.certificateRepository = certificateRepository;
        this.metheaAuthenticationService = metheaAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        boolean isNotAuthorize = true;
        PrincipalAuthentication authentication = null;
        String token = req.getHeader(SecurityConstants.HEADER_STRING);
        if (!StringUtils.isEmpty(token) && !WHITE_URL.equals(req.getRequestURI().split(MConstant.SLASH)[1])) {
            SystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MConstant.CERT_TYPE,
                    MConstant.ACTIVE_STATUS);
            if (ObjectUtils.isEmpty(certificate)) {
                throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
            }
            String user = JwtUtil.decodeToken(token.replace(SecurityConstants.TOKEN_PREFIX, StringUtils.EMPTY), certificate.getPrivateKey());
            if (!StringUtils.isEmpty(user)) {
                authentication = metheaAuthenticationService.loadUserByUsername(user);
            }
        }
        if (!ObjectUtils.isEmpty(authentication)) {
            String uri = StringUtils.EMPTY;
            String requestURI = req.getRequestURI();
            List<GrantedPermission> grantedPermissions = authentication.getGrantedPermissions();
            List<String> grantedURIs = CollectionUtils.emptyIfNull(grantedPermissions).stream()
                    .map(GrantedPermission::getGrantedPermission)
                    .collect(Collectors.toList());

            //>>>>> is it contain parent configuration?
            for (String str : requestURI.split(MConstant.SLASH)) {
                uri = uri.concat(MConstant.SLASH).concat(StringUtils.stripToEmpty(str));
                StringBuilder builder = new StringBuilder(uri);
                if (grantedURIs.contains(requestURI) || grantedURIs.contains((builder.deleteCharAt(0).toString() + MConstant.SLASH_STAR))) {
                    isNotAuthorize = false;
                }
            }
            //>>>>> is it config with this specific URI, will check on second condition when request "/"
            if (grantedURIs.contains(requestURI) || grantedURIs.contains(requestURI + MConstant.SLASH_STAR)
                    || grantedURIs.contains(requestURI + "**")) {
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
