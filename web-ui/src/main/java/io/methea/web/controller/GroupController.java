package io.methea.web.controller;

import io.methea.cache.MetheaCache;
import io.methea.constant.MetheaConstant;
import io.methea.controller.AbstractSimpleMetheaController;
import io.methea.domain.binder.GroupBinder;
import io.methea.domain.entity.TGroup;
import io.methea.domain.filter.GroupFilter;
import io.methea.domain.view.GroupView;
import io.methea.service.DataTableUIService;
import io.methea.service.GroupService;
import io.methea.service.DropdownService;
import io.methea.validator.GroupValidator;
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
@RequestMapping(value = GroupController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GroupController extends AbstractSimpleMetheaController<TGroup, GroupBinder, GroupView, GroupFilter> {

    static final String ROOT_URL = "/app/groups";
    private final DropdownService dropdownService;

    @Inject
    public GroupController(DataTableUIService dataTableUIService, DropdownService dropdownService,
                           GroupValidator groupValidator, GroupService groupService) {
        super(dataTableUIService);
        simpleMetheaService = groupService;
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
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
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
    protected TGroup getEntityFromBinder(GroupBinder binder) {
        TGroup group = new TGroup();
        group.setId(UUID.randomUUID().toString());
        return group;
    }

    @Override
    protected String getEntityId(GroupBinder binder) {
        return binder.getId();
    }
}

