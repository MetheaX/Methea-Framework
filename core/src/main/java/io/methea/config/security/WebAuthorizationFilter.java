package io.methea.config.security;

import io.methea.config.security.domain.GrantedPermission;
import io.methea.config.security.domain.PrincipalAuthentication;
import io.methea.constant.MConstant;
import io.methea.util.SystemUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebAuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        boolean isNotAuthorize = true;

        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute(MConstant.SPRING_SECURITY_CONTEXT);
        if (!ObjectUtils.isEmpty(securityContext)) {
            String uri = StringUtils.EMPTY;
            String requestURI = req.getRequestURI();
            Authentication user = securityContext.getAuthentication();

            PrincipalAuthentication principle = (PrincipalAuthentication) user.getPrincipal();
            List<GrantedPermission> grantedPermissions = principle.getGrantedPermissions();
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
                ((HttpServletResponse) response).sendRedirect(SystemUtils.getBaseUrl(req).concat("/access-denied"));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing here
    }
}
