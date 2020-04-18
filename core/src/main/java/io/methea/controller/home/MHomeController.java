package io.methea.controller.home;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.service.dropdown.MDropdownService;
import io.methea.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MHomeController {

    private static Logger log = LoggerFactory.getLogger(MHomeController.class);

    private static final String HOME_TEMPLATE_PATH = "home/home-page";
    private final MDropdownService dropdownService;

    @Inject
    public MHomeController(MDropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }

    @RequestMapping(value = {"/", "/app"})
    public String home(Model model, HttpServletRequest request) {
        if (CollectionUtils.isEmpty((Map<?, ?>) MCache.cacheMetaData.get(MConstant.DROPDOWN))) {
            dropdownService.getDropdownData();
            log.info(">>>>> Dropdown data loaded!");
        }
        model.addAttribute(MConstant.DROPDOWN, MCache.cacheMetaData.get(MConstant.DROPDOWN));
        model.addAttribute("contextPath", SystemUtils.getBaseUrl(request));
        return HOME_TEMPLATE_PATH;
    }
}
