package io.github.metheax.web.config.security;

import io.github.metheax.config.security.GrantedPermission;
import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.utils.SystemUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : DKSilverX
 * Date : 08/08/2020
 */
public class MetheaAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String CHN_PWD_URL = "/profile/change-password";
    private static final String ACCESS_DENIED_URL = "/access-denied";
    private static final String IS_FRC_CHN_PWD = "Y";

    // etc
    private static final List<String> WHITE_URLS = new ArrayList<String>() {

        private static final long serialVersionUID = 6092477020089934726L;

        {
        add("resources");
        add("login");
        add("logout");
        add("access-denied");
        add("profile");
    }};

    @Inject
    public MetheaAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        boolean isNotAuthorize = true;
        PrincipalAuthentication authentication;
        HttpSession session = req.getSession(true);
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute(MetheaConstant.SPRING_SECURITY_CONTEXT);
        if (!ObjectUtils.isEmpty(securityContext)) {
            Authentication user = securityContext.getAuthentication();
            authentication = (PrincipalAuthentication) user.getPrincipal();
            if (!ObjectUtils.isEmpty(authentication)) {
                String uri = StringUtils.EMPTY;
                String requestURI = req.getRequestURI();
                try {
                    if (IS_FRC_CHN_PWD.equalsIgnoreCase(authentication.getMetheaPrincipal().getForceUserResetPassword())
                            && !WHITE_URLS.contains(requestURI.split(MetheaConstant.SLASH)[1])) {
                        res.sendRedirect(SystemUtils.getBaseUrl(req).concat(CHN_PWD_URL));
                        return;
                    }
                } catch (Exception ex) {
                    res.sendRedirect(SystemUtils.getBaseUrl(req).concat(CHN_PWD_URL));
                    return;
                }
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
                    res.sendRedirect(SystemUtils.getBaseUrl(req).concat(ACCESS_DENIED_URL));
                    return;
                }
            }
        }
        chain.doFilter(req, res);
    }
}
