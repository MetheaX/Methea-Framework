package io.github.metheax.web.controller;

import io.github.metheax.controller.AbstractSimpleMetheaController;
import io.github.metheax.validator.APIBaseValidator;
import io.github.metheax.domain.binder.APIBaseBinder;
import io.github.metheax.domain.entity.TAPIBase;
import io.github.metheax.domain.filter.APIBaseFilter;
import io.github.metheax.domain.view.APIBaseView;
import io.github.metheax.service.DataTableUIService;
import io.github.metheax.service.APIBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {APIBaseController.ROOT_URL})
public class APIBaseController extends AbstractSimpleMetheaController<TAPIBase, APIBaseBinder, APIBaseView, APIBaseFilter> {

    static final String ROOT_URL = "/app/apibases";

    public APIBaseController(DataTableUIService dataTableUIService, APIBaseService service, APIBaseValidator validator) {
        super(dataTableUIService);
        super.validator = validator;
        super.simpleMetheaService = service;
        entity = "apibases";
        super.dataTableId = "tbl-apibases";
        configViewName = "apiBaseList";
        templatePath = "webservice/base-api/base-api";
    }

    @Override
    protected APIBaseFilter initFilter() {
        return new APIBaseFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(APIBaseFilter filter) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("apiUrl", "%".concat(filter.getApiUrl().toLowerCase()).concat("%"));
        parameters.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return parameters;
    }

    @Override
    protected TAPIBase getEntityFromBinder(APIBaseBinder binder){
        TAPIBase apiBase = new TAPIBase();
        apiBase.setId(UUID.randomUUID().toString());
        return apiBase;
    }

    @Override
    protected String getEntityId(APIBaseBinder binder) {
        return binder.getId();
    }
}
