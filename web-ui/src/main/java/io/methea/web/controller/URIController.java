package io.methea.web.controller;

import io.methea.controller.AbstractSimpleMetheaController;
import io.methea.domain.binder.URIBinder;
import io.methea.domain.entity.TResource;
import io.methea.domain.filter.URIFilter;
import io.methea.domain.view.URIView;
import io.methea.service.DataTableUIService;
import io.methea.service.URIService;
import io.methea.validator.URIValidator;
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
 * Date : 06/05/2020
 */
@Controller
@RequestMapping(value = URIController.ROOT_URL)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class URIController extends AbstractSimpleMetheaController<TResource, URIBinder, URIView, URIFilter> {
    static final String ROOT_URL = "/app/uris";

    @Inject
    public URIController(DataTableUIService dataTableUIService, URIValidator validator, URIService service) {
        super(dataTableUIService);
        super.validator = validator;
        super.simpleMetheaService = service;
        entity = "uris";
        super.dataTableId = "tbl-uris";
        configViewName = "uriList";
        templatePath = "configuration/uri/uri-list";
    }

    @Override
    protected URIFilter initFilter() {
        return new URIFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(URIFilter filter) {
        Map<String, Object> param = new HashMap<>();
        param.put("uriName", "%".concat(filter.getUriName().toLowerCase()).concat("%"));
        param.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return param;
    }

    @Override
    protected TResource getEntityFromBinder(URIBinder binder) {
        TResource uri = new TResource();
        uri.setId(UUID.randomUUID().toString());
        return uri;
    }

    @Override
    protected String getEntityId(URIBinder binder) {
        return binder.getId();
    }
}
