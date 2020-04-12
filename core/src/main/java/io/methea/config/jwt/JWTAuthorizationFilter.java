package io.methea.config.jwt;

import io.jsonwebtoken.Jwts;
import io.methea.constant.MConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author : DKSilverX
 * Date : 09/04/2018
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    private final Environment env;

    @Inject
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, @Lazy Environment env) {
        super(authenticationManager);
        this.env = env;
    }

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        // check if authenticated user from webservice, must validate they have a valid token
        if (!StringUtils.isEmpty(req.getHeader(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)))) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(req, res);
        }
        // otherwise this authenticated user is from UI
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY));

        if (!StringUtils.isEmpty(token)) {
            String user = Jwts.parser()
                    .setSigningKey(env.getProperty(MConstant.CLIENT_SECRET_KEY).getBytes())
                    .parseClaimsJws(token.replace(env.getProperty(MConstant.CLIENT_TOKEN_PREFIX).concat(MConstant.SPACE), StringUtils.EMPTY))
                    .getBody()
                    .getSubject();

            if (!StringUtils.isEmpty(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
