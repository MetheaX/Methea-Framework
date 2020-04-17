package io.methea.util;

import io.methea.config.security.domain.PrincipalAuthentication;
import io.methea.constant.MConstant;
import org.apache.commons.lang3.StringUtils;
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
        String userLoginId = StringUtils.EMPTY;
        HttpSession session = request.getSession(true);
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute(MConstant.SPRING_SECURITY_CONTEXT);
        if (!ObjectUtils.isEmpty(securityContext)) {
            Authentication user = securityContext.getAuthentication();
            userLoginId = ((PrincipalAuthentication) user.getPrincipal()).getUsername();
        }
        return userLoginId;
    }
}
