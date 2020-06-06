package io.methea.controller.webservice.apibase;

import io.methea.controller.abs.AbstractMetheaController;
import io.methea.domain.webservice.baseapi.dto.APIBaseBinder;
import io.methea.domain.webservice.baseapi.entity.APIBase;
import io.methea.domain.webservice.baseapi.filter.APIBaseFilter;
import io.methea.domain.webservice.baseapi.view.APIBaseView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.webservice.apibase.MAPIBaseService;
import io.methea.validator.webservice.apibase.APIBaseValidator;
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
@RequestMapping(value = {MAPIBaseController.ROOT_URL})
public class MAPIBaseController extends AbstractMetheaController<APIBase, APIBaseBinder, APIBaseView, APIBaseFilter> {

    static final String ROOT_URL = "/app/apibases";

    public MAPIBaseController(DataTableUIService dataTableUIService, MAPIBaseService service, APIBaseValidator validator) {
        super(dataTableUIService);
        super.validator = validator;
        super.metheaService = service;
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
    protected APIBase getEntityFromBinder(APIBaseBinder binder){
        APIBase apiBase = new APIBase();
        apiBase.setId(UUID.randomUUID().toString());
        return apiBase;
    }

    @Override
    protected String getEntityId(APIBaseBinder binder) {
        return binder.getId();
    }
}
