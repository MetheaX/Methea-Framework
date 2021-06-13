package io.github.metheax.utils;

import io.github.metheax.config.security.PrincipalAuthentication;
import io.github.metheax.constant.MetheaConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author : DKSilverX
 * Date : 02/02/2020
 */
public class PrincipalUtils {
    private PrincipalUtils() {
    }

    public static String getUserLoginId(HttpServletRequest request) {
        Authentication user = getAuthentication(request);
        if (ObjectUtils.isEmpty(user)) {
            return "System";
        }
        return ((PrincipalAuthentication) user.getPrincipal()).getUsername();
    }

    public static String getLoginGroupId(HttpServletRequest request) {
        Authentication user = getAuthentication(request);
        if (ObjectUtils.isEmpty(user)) {
            return "System";
        }
        return ((PrincipalAuthentication) user.getPrincipal()).getMetheaPrincipal().getGroupId();
    }

    private static Authentication getAuthentication(HttpServletRequest request) {
        Authentication user = null;
        try {
            HttpSession session = request.getSession(true);
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute(MetheaConstant.SPRING_SECURITY_CONTEXT);
            if (!ObjectUtils.isEmpty(securityContext)) {
                user = securityContext.getAuthentication();
            }
            return user;
        } catch (Exception ex) {
            return null;
        }
    }
}
