package io.methea.controller.configuration.group;

import io.methea.controller.abs.MBaseController;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MGroupController extends MBaseController {
    private static Logger log = LoggerFactory.getLogger(MGroupController.class);

    private static final String GET_ALL_GROUP_URL = "/groups";
    private static final String SAVE_GROUP_URL = "/groups/save";
    private static final String MODIFY_GROUP_URL = "/groups/modify";
    private static final String REDIRECT_URL = "redirect:/app/groups";
    private static final String GET_GROUP_BY_ID_URL = "/api/v1/groups";
    private static final String ACTIVATED_GROUP_URL = "/api/v1/groups/activate";
    private static final String DEACTIVATED_GROUP_URL = "/api/v1/groups/deactivate";

    private static final String GROUP_TEMPLATE_PATH = "configuration/group/group-list";

    private final DataTableUIService dataTableUIService;

    @Inject
    public MGroupController(DataTableUIService dataTableUIService) {
        this.dataTableUIService = dataTableUIService;
    }

    @RequestMapping(value = {GET_ALL_GROUP_URL})
    public String viewGroupList(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", SystemUtils.getBaseUrl(request));
        return GROUP_TEMPLATE_PATH;
    }
}

