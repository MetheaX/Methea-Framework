package io.methea.controller.auth;

import io.methea.constant.MConstant;
import io.methea.utils.SystemUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MUIAuthController {

    private static final String LOGIN_URL = "/login";
    private static final String ACCESS_DENIED = "/access-denied";

    private static final String LOGIN_TEMPLATE_PATH = "login/login-page";
    private static final String ACCESS_DENIED_TEMPLATE_PATH = "access-denied/access-denied-page";

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.GET)
    public String auth(Model model, HttpServletRequest request) {
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return LOGIN_TEMPLATE_PATH;
    }

    @RequestMapping(value = ACCESS_DENIED, method = RequestMethod.GET)
    public String accessDenied(Model model, HttpServletRequest request) {
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return ACCESS_DENIED_TEMPLATE_PATH;
    }
}
