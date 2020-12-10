package io.methea.web.controller.configuration.group;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.AbstractSimpleMetheaController;
import io.methea.domain.configuration.group.dto.UserGroupBinder;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.group.filter.GroupFilter;
import io.methea.domain.configuration.group.view.GroupView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.group.MGroupService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.validator.configuration.group.GroupValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@Controller
@RequestMapping(value = MGroupController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MGroupController extends AbstractSimpleMetheaController<TUserGroup, UserGroupBinder, GroupView, GroupFilter> {

    static final String ROOT_URL = "/app/groups";
    private final MDropdownService dropdownService;

    @Inject
    public MGroupController(DataTableUIService dataTableUIService, MDropdownService dropdownService,
                            GroupValidator groupValidator, MGroupService mGroupService) {
        super(dataTableUIService);
        simpleMetheaService = mGroupService;
        entity = "groups";
        super.dataTableId = "tbl-groups";
        configViewName = "groupList";
        templatePath = "configuration/group/group-list";
        this.dropdownService = dropdownService;
        validator = groupValidator;
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        dropdownService.refreshAccountDropdown();
        model.addAttribute(MConstant.DROPDOWN, MCache.CACHE_META_DATA.get(MConstant.DROPDOWN));
        return model;
    }

    @Override
    protected GroupFilter initFilter(){
        return new GroupFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(GroupFilter filter) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupName", "%".concat(filter.getGroupName().toLowerCase()).concat("%"));
        params.put("accountName", "%".concat(filter.getAccountName().toLowerCase()).concat("%"));
        params.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return params;
    }

    @Override
    protected TUserGroup getEntityFromBinder(UserGroupBinder binder) {
        TUserGroup group = new TUserGroup();
        group.setId(UUID.randomUUID().toString());
        return group;
    }

    @Override
    protected String getEntityId(UserGroupBinder binder) {
        return binder.getId();
    }
}

