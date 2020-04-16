package io.methea.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.methea.constant.MConstant;
import io.methea.domain.configuration.user.dto.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 * Author : DKSilverX
 * Date : 09/04/2018
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final Environment env;

    private UserLogin cred = null;

    @Inject
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, @Lazy Environment env) {
        this.authenticationManager = authenticationManager;
        this.env = env;
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
            try {
                this.cred = new ObjectMapper().readValue(req.getInputStream(), UserLogin.class);
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        this.cred.getUsername(),
                        this.cred.getPassword(),
                        new ArrayList<>()
                ));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                         FilterChain chain, Authentication auth) throws IOException, ServletException {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis() + Long.parseLong(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_TOKEN_EXPIRATION))
                ? JWTConstants.EXPIRATION_TIME : Objects.requireNonNull(env.getProperty(MConstant.CLIENT_TOKEN_EXPIRATION))));
        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.HS512, ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_SECRET_KEY))
                        ? JWTConstants.SECRET.getBytes() : Objects.requireNonNull(env.getProperty(MConstant.CLIENT_SECRET_KEY)).getBytes())
                .compact();
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(((User) auth.getPrincipal())));
        // if authenticate request from webservice success, give access token to client
        if (!ObjectUtils.isEmpty(cred)) {
            res.addHeader(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)) ? JWTConstants.HEADER_STRING
                    : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY), token);
        }
        // otherwise if authenticate success from UI, redirect to home page
        else {
            successHandler.onAuthenticationSuccess(req, res, auth);
        }
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                           AuthenticationException failed) throws IOException, ServletException {
        response.addHeader("Authentication Failed ::", failed.getMessage());
    }

    private UsernamePasswordAuthenticationToken getAuthentication(User user) {
        if (!ObjectUtils.isEmpty(user)) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                    user.getAuthorities());
        }
        return null;
    }
}
