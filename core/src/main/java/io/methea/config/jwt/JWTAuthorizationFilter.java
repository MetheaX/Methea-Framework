package io.methea.config.jwt;

import io.jsonwebtoken.Jwts;
import io.methea.constant.MConstant;
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
import java.util.ArrayList;
import java.util.Objects;

/**
 * Author : DKSilverX
 * Date : 09/04/2018
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final Environment env;

    @Inject
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, @Lazy Environment env) {
        super(authenticationManager);
        this.env = env;
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

        if (!StringUtils.isEmpty(token)) {
            String user = Jwts.parser()
                    .setSigningKey(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_SECRET_KEY))
                            ? JWTConstants.SECRET.getBytes() : Objects.requireNonNull(env.getProperty(MConstant.CLIENT_SECRET_KEY)).getBytes())
                    .parseClaimsJws(token.replace(ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_TOKEN_PREFIX))
                            ? JWTConstants.TOKEN_PREFIX.concat(MConstant.SPACE)
                            : Objects.requireNonNull(env.getProperty(MConstant.CLIENT_TOKEN_PREFIX)).concat(MConstant.SPACE), StringUtils.EMPTY))
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
