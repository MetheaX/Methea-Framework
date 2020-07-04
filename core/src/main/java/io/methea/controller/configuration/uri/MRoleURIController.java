package io.methea.controller.configuration.uri;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.abs.AbstractSimpleMetheaController;
import io.methea.domain.configuration.uri.dto.RoleURIBinder;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.uri.filter.RoleURIFilter;
import io.methea.domain.configuration.uri.view.RoleURIView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.uri.RoleURIService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.validator.configuration.uri.RoleURIValidator;
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
 * Date : 16/05/2020
 */
@Controller
@RequestMapping(value = MRoleURIController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MRoleURIController extends AbstractSimpleMetheaController<TRoleURI, RoleURIBinder, RoleURIView, RoleURIFilter> {

    static final String ROOT_URL = "/app/roleURIs";
    private final MDropdownService dropdownService;

    @Inject
    public MRoleURIController(DataTableUIService dataTableUIService, MDropdownService dropdownService,
                              RoleURIValidator roleURIValidator, RoleURIService service) {
        super(dataTableUIService);
        metheaService = service;
        entity = "roleURIs";
        super.dataTableId = "tbl-role-uris";
        configViewName = "roleURIsList";
        templatePath = "configuration/uri/role-uri-list";
        this.dropdownService = dropdownService;
        validator = roleURIValidator;
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
    protected RoleURIFilter initFilter() {
        return new RoleURIFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(RoleURIFilter filter) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", "%".concat(filter.getRoleName().toLowerCase()).concat("%"));
        params.put("uriName", "%".concat(filter.getUriName().toLowerCase()).concat("%"));
        params.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return params;
    }

    @Override
    protected TRoleURI getEntityFromBinder(RoleURIBinder binder) {
        TRoleURI roleURI = new TRoleURI();
        roleURI.setId(UUID.randomUUID().toString());
        return roleURI;
    }

    @Override
    protected String getEntityId(RoleURIBinder binder) {
        return binder.getId();
    }
}
