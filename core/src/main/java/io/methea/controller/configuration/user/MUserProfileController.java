package io.methea.controller.configuration.user;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

/**
 * Author : DKSilverX
 * Date : 30/04/2020
 */
@Controller
@RequestMapping(value = MUserProfileController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MUserProfileController {

    static final String ROOT_URL = "/profile";
    private static final String FORCE_USR_CHG_PWD = "/change-password";

    @ResponseBody
    @RequestMapping(value = FORCE_USR_CHG_PWD, method = RequestMethod.GET)
    public String resetUserPassword() {
        return "Hey welcome! You have to change password";
    }
}
