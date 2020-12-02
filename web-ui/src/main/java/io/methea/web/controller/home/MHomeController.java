package io.methea.web.controller.home;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.service.dropdown.MDropdownService;
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
public class MHomeController {

    private static final String HOME_TEMPLATE_PATH = "home/home-page";
    private final MDropdownService dropdownService;

    @Inject
    public MHomeController(MDropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }

    @GetMapping(value = {"/", "/app"})
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute(MConstant.DROPDOWN, MCache.CACHE_META_DATA.get(MConstant.DROPDOWN));
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute(MConstant.CORE_MENU, MCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
        return HOME_TEMPLATE_PATH;
    }
}
