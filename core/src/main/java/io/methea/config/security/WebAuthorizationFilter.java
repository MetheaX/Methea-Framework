package io.methea.config.security;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.permission.TUserPermission;
import io.methea.util.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebAuthorizationFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(WebAuthorizationFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        boolean isNotValidURI = true;

        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute(MConstant.SPRING_SECURITY_CONTEXT);
        if (!ObjectUtils.isEmpty(securityContext)) {
            Authentication user = securityContext.getAuthentication();
            String uri = req.getRequestURI();

            List<TUserPermission> uris = (List<TUserPermission>) user.getAuthorities();
            List<String> grantedURI = new ArrayList<>();

            //>>>>> get available URIs from security context
            if (!CollectionUtils.isEmpty(uris)) {
                grantedURI = uris.stream()
                        .map(TUserPermission::getUriName)
                        .collect(Collectors.toList());
            }
            if (StringUtils.isNotEmpty(uri)) {
                String[] subURI = uri.split(MConstant.SLASH);
                String specificURI = uri;
                uri = StringUtils.EMPTY; //>>>>> reset URI value

                //>>>>> is it contain parent configuration?
                for (String str : subURI) {
                    if(StringUtils.isNotEmpty(str)){
                        uri = uri.concat(MConstant.SLASH).concat(str);
                    }
                    //>>>>> check if, is it qualify to pass our security guard
                    if (grantedURI.contains((uri + MConstant.SLASH_STAR))) {
                        isNotValidURI = false;
                    }
                }
                //>>>>> is it config with this specific URI, will check on second condition will subURI length == 0
                if (grantedURI.contains(specificURI) || grantedURI.contains((uri + MConstant.SLASH_STAR))) {
                    isNotValidURI = false;
                }
                //>>>>> You cannot fake our security gard, go to access denied page
                if (isNotValidURI) {
                    ((HttpServletResponse) response).sendRedirect(SystemUtils.getBaseUrl(req).concat("/access-denied"));
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
