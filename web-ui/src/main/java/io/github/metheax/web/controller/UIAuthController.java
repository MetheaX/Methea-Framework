package io.github.metheax.web.controller;

import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.utils.SystemUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UIAuthController {

    private static final String LOGIN_URL = "/login";
    private static final String ACCESS_DENIED = "/access-denied";

    private static final String LOGIN_TEMPLATE_PATH = "login/login-page";
    private static final String ACCESS_DENIED_TEMPLATE_PATH = "access-denied/access-denied-page";

    @GetMapping(value = LOGIN_URL)
    public String auth(Model model, HttpServletRequest request) {
        model.addAttribute(MetheaConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return LOGIN_TEMPLATE_PATH;
    }

    @GetMapping(value = ACCESS_DENIED)
    public String accessDenied(Model model, HttpServletRequest request) {
        model.addAttribute(MetheaConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        return ACCESS_DENIED_TEMPLATE_PATH;
    }
}
