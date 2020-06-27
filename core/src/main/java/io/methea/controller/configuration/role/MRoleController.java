package io.methea.controller.configuration.role;

import io.methea.controller.abs.AbstractSimpleMetheaController;
import io.methea.domain.configuration.role.dto.RoleBinder;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.role.filter.RoleFilter;
import io.methea.domain.configuration.role.view.RoleView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.role.MRoleService;
import io.methea.validator.configuration.role.RoleValidator;
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
@RequestMapping(value = MRoleController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MRoleController extends AbstractSimpleMetheaController<TRole, RoleBinder, RoleView, RoleFilter> {
    static final String ROOT_URL = "/app/roles";

    @Inject
    public MRoleController(DataTableUIService dataTableUIService, RoleValidator validator, MRoleService service) {
        super(dataTableUIService);
        super.validator = validator;
        super.metheaService = service;
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
