package io.methea.controller.home;

import io.methea.util.SystemUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MHomeController {

    private static final String HOME_TEMPLATE_PATH = "home/home-page";

    @RequestMapping(value = {"/", "/app"})
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", SystemUtils.getBaseUrl(request));
        return HOME_TEMPLATE_PATH;
    }
}
