package io.methea.controller.configuration.display;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.controller.abs.AbstractMetheaController;
import io.methea.domain.configuration.display.dto.DataTableBinder;
import io.methea.domain.configuration.display.entity.TDataTableView;
import io.methea.domain.configuration.display.filter.DataTableFilter;
import io.methea.domain.configuration.display.view.DataTableView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.display.MDataTableService;
import io.methea.service.dropdown.MDropdownService;
import io.methea.validator.configuration.display.DataTableValidator;
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
 * Date : 08/06/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {MDataTableController.ROOT_URL})
public class MDataTableController extends AbstractMetheaController<TDataTableView, DataTableBinder, DataTableView, DataTableFilter> {
    static final String ROOT_URL = "/app/datatable";
    private final MDropdownService dropdownService;

    @Inject
    public MDataTableController(DataTableUIService dataTableUIService, MDropdownService dropdownService,
                                MDataTableService service, DataTableValidator validator) {
        super(dataTableUIService);
        this.dropdownService = dropdownService;
        super.validator = validator;
        super.metheaService = service;
        entity = "datatable";
        super.dataTableId = "tbl-datatable";
        configViewName = "dataTableList";
        templatePath = "configuration/display/datatable-list";
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        if (CollectionUtils.isEmpty((Map<?, ?>) MCache.cacheMetaData.get(MConstant.DROPDOWN))) {
            dropdownService.getDropdownData();
        }
        model.addAttribute(MConstant.DROPDOWN, MCache.cacheMetaData.get(MConstant.DROPDOWN));
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
