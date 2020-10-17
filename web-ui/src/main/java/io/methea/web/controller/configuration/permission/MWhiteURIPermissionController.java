package io.methea.web.controller.configuration.permission;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.AbstractSimpleMetheaController;
import io.methea.domain.configuration.permission.dto.WhiteURIPermissionBinder;
import io.methea.domain.configuration.permission.entity.TWhiteURIPermission;
import io.methea.domain.configuration.permission.filter.WhiteURIPermissionFilter;
import io.methea.domain.configuration.permission.view.WhiteURIPermissionView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.permission.MWhiteURIPermissionService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.validator.configuration.permission.WhiteURIPermissionValidator;
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
 * Date : 17/10/2020
 */
@Controller
@RequestMapping(value = MWhiteURIPermissionController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MWhiteURIPermissionController extends AbstractSimpleMetheaController<TWhiteURIPermission, WhiteURIPermissionBinder,
        WhiteURIPermissionView, WhiteURIPermissionFilter> {
    static final String ROOT_URL = "/app/white-urls";

    private final MDropdownService dropdownService;

    @Inject
    public MWhiteURIPermissionController(DataTableUIService dataTableUIService, MDropdownService dropdownService,
                                         WhiteURIPermissionValidator validator, MWhiteURIPermissionService service) {
        super(dataTableUIService);
        this.dropdownService = dropdownService;
        super.validator = validator;
        super.metheaService = service;
        entity = "whiteurls";
        super.dataTableId = "tbl-whiteurls";
        configViewName = "whiteurlList";
        templatePath = "configuration/permission/white-urls";
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
    protected WhiteURIPermissionFilter initFilter() {
        return new WhiteURIPermissionFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(WhiteURIPermissionFilter filter) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "%".concat(filter.getUriName().toLowerCase()).concat("%"));
        param.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return param;
    }

    @Override
    protected TWhiteURIPermission getEntityFromBinder(WhiteURIPermissionBinder binder) {
        TWhiteURIPermission whiteURIPermission = new TWhiteURIPermission();
        whiteURIPermission.setId(UUID.randomUUID().toString());
        return whiteURIPermission;
    }

    @Override
    protected String getEntityId(WhiteURIPermissionBinder binder) {
        return binder.getId();
    }
}
