package io.methea.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.methea.config.security.domain.PrincipalAuthentication;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.user.dto.UserLogin;
import io.methea.domain.webservice.system.entity.SystemCertificate;
import io.methea.domain.webservice.client.dto.ClientAuthentication;
import io.methea.exception.CertificateNotFoundException;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import io.methea.service.auth.CustomAuthenticationService;
import io.methea.utils.SystemUtils;
import io.methea.utils.auth.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 09/04/2018
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final CustomAuthenticationService customAuthenticationService;
    private final Environment env;
    private final SystemCertificateRepository certificateRepository;

    private ClientAuthentication authentication = null;

    @Inject
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   CustomAuthenticationService customAuthenticationService, @Lazy Environment env,
                                   SystemCertificateRepository certificateRepository) {
        this.env = env;
        this.authenticationManager = authenticationManager;
        this.certificateRepository = certificateRepository;
        this.customAuthenticationService = customAuthenticationService;
    }

    private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        // it the request from login screen [UI]
        if (StringUtils.isNotEmpty(req.getParameter("username"))) {
            try {
                UserLogin cred = new UserLogin(req.getParameter("username"), req.getParameter("password"));
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        cred.getUsername(),
                        cred.getPassword(),
                        new ArrayList<>()
                ));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // it request from webservices
            return authenticate(req);
        }
    }

    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        try {
            this.authentication = new ObjectMapper().readValue(request.getInputStream(), ClientAuthentication.class);
            if (!ObjectUtils.isEmpty(this.authentication)) {
                PrincipalAuthentication authentication = customAuthenticationService.loadClientByClientId(this.authentication);
                if (!ObjectUtils.isEmpty(authentication)) {
                    return new UsernamePasswordAuthenticationToken(authentication, authentication.getPassword(), authentication.getAuthorities());
                }
                return null;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                         FilterChain chain, Authentication auth) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(((PrincipalAuthentication) auth.getPrincipal())));
        if (!ObjectUtils.isEmpty(authentication)) {
            SystemCertificate certificate = certificateRepository.findSystemCertificateByCodeAndStatus(MConstant.CERT_TYPE,
                    MConstant.ACTIVE_STATUS);
            if (ObjectUtils.isEmpty(certificate)) {
                throw new CertificateNotFoundException("No active certificate could be found! Please check system certificate!");
            }
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(cal.getTimeInMillis() + Long.parseLong(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_TOKEN_EXPIRATION))
                    ? JWTConstants.EXPIRATION_TIME : Objects.requireNonNull(env.getProperty(MConstant.CLIENT_TOKEN_EXPIRATION))));
            Map<String, String> map = JwtUtil.encodeToken(certificate.getPublicKey(),
                    ((PrincipalAuthentication) auth.getPrincipal()).getUsername(), SystemUtils.getBaseUrl(req), cal);

            res.addHeader(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)) ? JWTConstants.HEADER_STRING
                    : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY), map.get(MConstant.JWT_TOKEN));
            res.addHeader(MConstant.EXPIRED_IN, String.valueOf(cal.getTimeInMillis()));
        } else {
            successHandler.onAuthenticationSuccess(req, res, auth);
        }
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                           AuthenticationException failed) throws IOException, ServletException {

        response.addHeader("Authentication Failed ::", failed.getMessage());
    }

    private UsernamePasswordAuthenticationToken getAuthentication(PrincipalAuthentication principal) {
        if (!ObjectUtils.isEmpty(principal)) {
            return new UsernamePasswordAuthenticationToken(principal, null,
                    principal.getAuthorities());
        }
        return null;
    }
}
