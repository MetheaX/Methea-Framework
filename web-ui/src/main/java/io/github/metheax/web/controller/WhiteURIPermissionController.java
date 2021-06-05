package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.controller.AbstractSimpleMetheaController;
import io.github.metheax.service.DataTableUIService;
import io.github.metheax.service.DropdownService;
import io.github.metheax.service.MWhiteURIPermissionService;
import io.github.metheax.domain.binder.WhiteURIPermissionBinder;
import io.github.metheax.domain.entity.TPublicPermission;
import io.github.metheax.domain.filter.WhiteURIPermissionFilter;
import io.github.metheax.domain.view.WhiteURIPermissionView;
import io.github.metheax.validator.WhiteURIPermissionValidator;
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
 * Date : 17/10/2020
 */
@Controller
@RequestMapping(value = WhiteURIPermissionController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WhiteURIPermissionController extends AbstractSimpleMetheaController<TPublicPermission, WhiteURIPermissionBinder,
        WhiteURIPermissionView, WhiteURIPermissionFilter> {
    static final String ROOT_URL = "/app/whiteurls";

    private final DropdownService dropdownService;

    @Inject
    public WhiteURIPermissionController(DataTableUIService dataTableUIService, DropdownService dropdownService,
                                        WhiteURIPermissionValidator validator, MWhiteURIPermissionService service) {
        super(dataTableUIService);
        this.dropdownService = dropdownService;
        super.validator = validator;
        super.simpleMetheaService = service;
        entity = "whiteurls";
        super.dataTableId = "tbl-whiteurls";
        configViewName = "whiteurlList";
        templatePath = "configuration/permission/white-urls";
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
        return model;
    }

    @Override
    protected WhiteURIPermissionFilter initFilter() {
        return new WhiteURIPermissionFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(WhiteURIPermissionFilter filter) {
        Map<String, Object> param = new HashMap<>();
        param.put("uriName", "%".concat(filter.getUriName().toLowerCase()).concat("%"));
        param.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return param;
    }

    @Override
    protected TPublicPermission getEntityFromBinder(WhiteURIPermissionBinder binder) {
        TPublicPermission whiteURIPermission = new TPublicPermission();
        whiteURIPermission.setId(UUID.randomUUID().toString());
        return whiteURIPermission;
    }

    @Override
    protected String getEntityId(WhiteURIPermissionBinder binder) {
        return binder.getId();
    }
}
