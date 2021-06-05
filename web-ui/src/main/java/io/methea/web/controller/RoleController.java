package io.methea.web.controller;

import io.methea.controller.AbstractSimpleMetheaController;
import io.methea.domain.binder.RoleBinder;
import io.methea.domain.entity.TRole;
import io.methea.domain.filter.RoleFilter;
import io.methea.domain.view.RoleView;
import io.methea.service.DataTableUIService;
import io.methea.service.RoleService;
import io.methea.validator.RoleValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@Controller
@RequestMapping(value = RoleController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RoleController extends AbstractSimpleMetheaController<TRole, RoleBinder, RoleView, RoleFilter> {
    static final String ROOT_URL = "/app/roles";

    @Inject
    public RoleController(DataTableUIService dataTableUIService, RoleValidator validator, RoleService service) {
        super(dataTableUIService);
        super.validator = validator;
        super.simpleMetheaService = service;
        entity = "roles";
        super.dataTableId = "tbl-roles";
        configViewName = "roleList";
        templatePath = "configuration/role/role-list";
    }

    @Override
    protected RoleFilter initFilter() {
        return new RoleFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(RoleFilter filter) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "%".concat(filter.getName().toLowerCase()).concat("%"));
        param.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return param;
    }

    @Override
    protected TRole getEntityFromBinder(RoleBinder binder) {
        TRole role = new TRole();
        role.setId(UUID.randomUUID().toString());
        return role;
    }

    @Override
    protected String getEntityId(RoleBinder binder) {
        return binder.getId();
    }
}
