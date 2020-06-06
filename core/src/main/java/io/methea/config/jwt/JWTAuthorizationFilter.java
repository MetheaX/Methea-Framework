package io.methea.config.jwt;

import io.methea.config.security.domain.PrincipalAuthentication;
import io.methea.constant.MConstant;
import io.methea.domain.webservice.system.entity.SystemCertificate;
import io.methea.exception.CertificateNotFoundException;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import io.methea.service.auth.CustomAuthenticationService;
import io.methea.utils.auth.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
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

/**
 * Author : DKSilverX
 * Date : 09/04/2018
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final Environment env;
    private final CustomAuthenticationService authenticationService;
    private final SystemCertificateRepository certificateRepository;

    @Inject
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, @Lazy Environment env,
                                  CustomAuthenticationService authenticationService, SystemCertificateRepository certificateRepository) {
        super(authenticationManager);
        this.env = env;
        this.certificateRepository = certificateRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        // check if authenticated user from webservice, must validate they have a valid token
        if (!StringUtils.isEmpty(req.getHeader(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY))
                ? JWTConstants.HEADER_STRING : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)))) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(req, res);
        }
        // otherwise this authenticated user is from UI
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY))
                ? JWTConstants.HEADER_STRING : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY));
        SystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MConstant.CERT_TYPE,
                MConstant.ACTIVE_STATUS);
        if (ObjectUtils.isEmpty(certificate)) {
            throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
        }
        if (!StringUtils.isEmpty(token)) {
            String user = JwtUtil.decodeToken(token.replace(JWTConstants.TOKEN_PREFIX, StringUtils.EMPTY), certificate.getPrivateKey());
            if (!StringUtils.isEmpty(user)) {
                PrincipalAuthentication authentication = authenticationService.loadClientByClientId(user);
                return new UsernamePasswordAuthenticationToken(authentication, null, authentication.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
