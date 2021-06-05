package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.controller.AbstractSimpleMetheaController;
import io.github.metheax.domain.entity.TDataTableView;
import io.github.metheax.service.DropdownService;
import io.github.metheax.validator.DataTableValidator;
import io.github.metheax.domain.binder.DataTableBinder;
import io.github.metheax.domain.filter.DataTableFilter;
import io.github.metheax.domain.view.DataTableView;
import io.github.metheax.service.DataTableUIService;
import io.github.metheax.service.DataTableService;
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
 * Date : 08/06/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {DataTableController.ROOT_URL})
public class DataTableController extends AbstractSimpleMetheaController<TDataTableView, DataTableBinder, DataTableView, DataTableFilter> {
    static final String ROOT_URL = "/app/datatable";
    private final DropdownService dropdownService;

    @Inject
    public DataTableController(DataTableUIService dataTableUIService, DropdownService dropdownService,
                               DataTableService service, DataTableValidator validator) {
        super(dataTableUIService);
        this.dropdownService = dropdownService;
        super.validator = validator;
        super.simpleMetheaService = service;
        entity = "datatable";
        super.dataTableId = "tbl-datatable";
        configViewName = "dataTableList";
        templatePath = "configuration/display/datatable-list";
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
        return model;
    }

    @Override
    protected DataTableFilter initFilter() {
        return new DataTableFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(DataTableFilter filter) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("viewName", "%".concat(filter.getViewName().toLowerCase()).concat("%"));
        parameters.put("labelColumnHead", "%".concat(filter.getLabelColumnHead().toLowerCase()).concat("%"));
        parameters.put("allowFilter", "%".concat(filter.getAllowFilter().toLowerCase()).concat("%"));
        parameters.put("columnKey", "%".concat(filter.getColumnKey().toLowerCase()).concat("%"));
        parameters.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return parameters;
    }

    @Override
    protected TDataTableView getEntityFromBinder(DataTableBinder binder) {
        TDataTableView view = new TDataTableView();
        view.setId(UUID.randomUUID().toString());
        return view;
    }

    @Override
    protected String getEntityId(DataTableBinder binder) {
        return binder.getId();
    }
}
