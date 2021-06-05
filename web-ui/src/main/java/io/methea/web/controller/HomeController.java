package io.methea.web.controller;

import io.methea.cache.MetheaCache;
import io.methea.constant.MetheaConstant;
import io.methea.service.DropdownService;
import io.methea.utils.PrincipalUtils;
import io.methea.utils.SystemUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HomeController {

    private static final String HOME_TEMPLATE_PATH = "home/home-page";
    private final DropdownService dropdownService;

    @Inject
    public HomeController(DropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }

    @GetMapping(value = {"/", "/app"})
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
        model.addAttribute(MetheaConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute(MetheaConstant.CORE_MENU, MetheaCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
        return HOME_TEMPLATE_PATH;
    }
}
