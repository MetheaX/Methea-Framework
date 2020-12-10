package io.methea.web.controller.configuration.user;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.user.dto.UserBinder;
import io.methea.service.configuration.user.MUserService;
import io.methea.utils.SystemUtils;
import io.methea.validator.configuration.user.UserValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 30/04/2020
 */
@Controller
@RequestMapping(value = MUserProfileController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MUserProfileController {

    static final String ROOT_URL = "/profile";
    private static final String FORCE_USR_CHG_PWD_URL = "/change-password";
    private static final String SAVE_CHG_PWD_URL = "/change-password/save";

    private static final String TEMPLATE_PATH = "profile/change-password";

    private final UserValidator validator;
    private final MUserService service;

    @Inject
    public MUserProfileController(UserValidator validator, MUserService service) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping(value = FORCE_USR_CHG_PWD_URL)
    public String resetUserPassword(Model model, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("password", StringUtils.EMPTY);
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("errors", errors);
        return TEMPLATE_PATH;
    }

    @PostMapping(value = SAVE_CHG_PWD_URL)
    public ModelAndView saveChangePassword(Model model, UserBinder binder, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("validateType", "CHN");
        validator.validate(binder, errors);
        if (!CollectionUtils.isEmpty(errors)) {
            model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", errors);
            return new ModelAndView(TEMPLATE_PATH);
        }
        binder.setForceUserResetPassword("N");
        service.resetUserPassword(binder.getId(), binder);
        return new ModelAndView("redirect:/logout");
    }
}
