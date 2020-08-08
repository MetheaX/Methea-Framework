package io.methea.web.controller.configuration.permission;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.AbstractSimpleMetheaController;
import io.methea.domain.configuration.permission.dto.RMPermissionBinder;
import io.methea.domain.configuration.permission.entity.TRMUserPermission;
import io.methea.domain.configuration.permission.filter.RMPermissionFilter;
import io.methea.domain.configuration.permission.view.RMPermissionView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.permission.MRMPermissionService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.validator.configuration.permission.RMPermissionValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
@Controller
@RequestMapping(value = MPermissionController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MPermissionController extends AbstractSimpleMetheaController<TRMUserPermission, RMPermissionBinder,
        RMPermissionView, RMPermissionFilter> {
    static final String ROOT_URL = "/app/permissions";

    private final MDropdownService dropdownService;

    @Inject
    public MPermissionController(DataTableUIService dataTableUIService, MDropdownService dropdownService, RMPermissionValidator validator,
                                 MRMPermissionService service) {
        super(dataTableUIService);
        this.dropdownService = dropdownService;
        super.validator = validator;
        super.metheaService = service;
        entity = "permissions";
        super.dataTableId = "tbl-permissions";
        configViewName = "permissionList";
        templatePath = "configuration/permission/user-permission";
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        if (CollectionUtils.isEmpty((Map<?, ?>) MCache.CACHE_META_DATA.get(MConstant.DROPDOWN))) {
            dropdownService.getDropdownData();
        }
        model.addAttribute(MConstant.DROPDOWN, MCache.CACHE_META_DATA.get(MConstant.DROPDOWN));
        return model;
    }

    @Override
    protected RMPermissionFilter initFilter() {
        return new RMPermissionFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(RMPermissionFilter filter) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "%".concat(filter.getUserLoginId().toLowerCase()).concat("%"));
        param.put("firstName", "%".concat(filter.getFirstName().toLowerCase()).concat("%"));
        param.put("lastName", "%".concat(filter.getLastName().toLowerCase()).concat("%"));
        param.put("roleName", "%".concat(filter.getRoleName().toLowerCase()).concat("%"));
        param.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return param;
    }

    @Override
    protected TRMUserPermission getEntityFromBinder(RMPermissionBinder binder) {
        TRMUserPermission permission = new TRMUserPermission();
        permission.setId(UUID.randomUUID().toString());
        return permission;
    }

    @Override
    protected String getEntityId(RMPermissionBinder binder) {
        return binder.getId();
    }
}
