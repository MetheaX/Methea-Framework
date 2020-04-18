package io.methea.controller.configuration.group;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.abs.MBaseController;
import io.methea.domain.configuration.group.dto.UserGroupBinder;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.group.filter.GroupFilter;
import io.methea.domain.configuration.group.view.GroupView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.group.MGroupService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.util.Pagination;
import io.methea.util.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MGroupController extends MBaseController {
    private static Logger log = LoggerFactory.getLogger(MGroupController.class);

    private static final String GET_ALL_GROUP_URL = "/groups";
    private static final String SAVE_GROUP_URL = "/group/save";
    private static final String MODIFY_GROUP_URL = "/group/modify";
    private static final String REDIRECT_URL = "redirect:/app/groups";
    private static final String GET_GROUP_BY_ID_URL = "/api/v1/groups";
    private static final String ACTIVATED_GROUP_URL = "/api/v1/groups/activate";
    private static final String DEACTIVATED_GROUP_URL = "/api/v1/groups/deactivate";

    private static final String GROUP_TEMPLATE_PATH = "configuration/group/group-list";

    private static final String VIEW_NAME = "groupList";

    private final DataTableUIService dataTableUIService;
    private final MDropdownService dropdownService;
    private final MGroupService mGroupService;

    @Inject
    public MGroupController(DataTableUIService dataTableUIService, MDropdownService dropdownService, MGroupService mGroupService) {
        this.dataTableUIService = dataTableUIService;
        this.dropdownService = dropdownService;
        this.mGroupService = mGroupService;
    }

    @RequestMapping(value = {GET_ALL_GROUP_URL})
    public String viewGroupList(Model model, GroupFilter filter, Pagination pagination, HttpServletRequest request) {

        if (CollectionUtils.isEmpty((List<?>) MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_KEY)))
                || CollectionUtils.isEmpty((List<?>) MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_LABEL)))) {
            dataTableUIService.getMetaTableConfiguration(VIEW_NAME);
            log.info(">>>>> Fetch meta data of group's datatable.");
        }
        Map<String, List<GroupView>> map = new HashMap<>();

        Map<String, Object> params = new HashMap<>();
        params.put("groupName", "%".concat(filter.getGroupName()).concat("%"));
        params.put("accountName", "%".concat(filter.getAccountName().toLowerCase()).concat("%"));
        params.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));

        map.put("data", mGroupService.getAllEntityViewByFilter(params, pagination));

        if (CollectionUtils.isEmpty((Map<?, ?>) MCache.cacheMetaData.get(MConstant.DROPDOWN))) {
            dropdownService.getDropdownData();
            log.info(">>>>> Dropdown data loaded!");
        }

        model.addAttribute("contextPath", SystemUtils.getBaseUrl(request));
        model.addAttribute("tableHead", MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_LABEL)));
        model.addAttribute("tableColumns", MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_KEY)));
        model.addAttribute("tableFilterColumns", MCache.cacheMetaData.get(VIEW_NAME.concat(MConstant.COLUMNS_FILTER)));
        model.addAttribute("groups", map);
        model.addAttribute(MConstant.DROPDOWN, MCache.cacheMetaData.get(MConstant.DROPDOWN));
        model.addAttribute("dataFilters", filter);
        model.addAttribute("pagination", pagination);
        return GROUP_TEMPLATE_PATH;
    }

    @RequestMapping(value = {SAVE_GROUP_URL})
    public ModelAndView saveGroup(UserGroupBinder binder) {
        TUserGroup group = new TUserGroup();
        group.setId(UUID.randomUUID().toString());
        mGroupService.saveEntity(group, binder);
        return new ModelAndView(REDIRECT_URL);
    }
}

