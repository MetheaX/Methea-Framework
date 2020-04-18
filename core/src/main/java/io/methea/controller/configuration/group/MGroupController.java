package io.methea.controller.configuration.group;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.abs.AbstractMetheaController;
import io.methea.domain.configuration.group.dto.UserGroupBinder;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.group.filter.GroupFilter;
import io.methea.domain.configuration.group.view.GroupView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.group.MGroupService;
import io.methea.service.dropdown.MDropdownService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
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
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {MGroupController.ROOT_URL})
public class MGroupController extends AbstractMetheaController<TUserGroup, UserGroupBinder, GroupView, GroupFilter> {

    private static Logger log = LoggerFactory.getLogger(MGroupController.class);

    static final String ROOT_URL = "/app/groups";
    private final MDropdownService dropdownService;

    @Inject
    public MGroupController(DataTableUIService dataTableUIService, MDropdownService dropdownService, MGroupService mGroupService) {
        super(dataTableUIService);
        metheaService = mGroupService;
        entity = "groups";
        configViewName = "groupList";
        templatePath = "configuration/group/group-list";
        this.dropdownService = dropdownService;
    }

    protected Model getExtraAttribute(Model model) {
        if (CollectionUtils.isEmpty((Map<?, ?>) MCache.cacheMetaData.get(MConstant.DROPDOWN))) {
            dropdownService.getDropdownData();
            log.info(">>>>> Dropdown data loaded!");
        }
        model.addAttribute(MConstant.DROPDOWN, MCache.cacheMetaData.get(MConstant.DROPDOWN));
        return model;
    }

    @Override
    protected Map<String, Object> getFilterColumns(GroupFilter filter) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupName", "%".concat(filter.getGroupName()).concat("%"));
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

