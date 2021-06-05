package io.github.metheax.controller;

import io.github.metheax.domain.entity.abs.AbstractMetheaEntity;
import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.binder.abs.AbstractMetheaBinder;
import io.github.metheax.domain.filter.abs.AbstractMetheaFilter;
import io.github.metheax.domain.view.abs.AbstractMetheaView;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
import io.github.metheax.service.DataTableUIService;
import io.github.metheax.utils.Pagination;
import io.github.metheax.utils.PrincipalUtils;
import io.github.metheax.utils.SystemUtils;
import io.github.metheax.validator.abs.AbstractMetheaValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public abstract class AbstractSimpleMetheaController<E extends AbstractMetheaEntity<E>, B extends AbstractMetheaBinder<B>,
        V extends AbstractMetheaView<V>, F extends AbstractMetheaFilter<F>> {
    protected static final String ROOT_URL = "/app";
    private static final String SAVE_URL = "/save";
    private static final String MODIFY_URL = "/modify";
    private static final String API_GET_BY_ID = "/rest";
    private static final String API_GET_ENTITY_BY_ID = "/rest/entity";
    private static final String API_ACTIVATE_URL = "/rest/activate";
    private static final String API_DEACTIVATE_URL = "/rest/deactivate";

    protected String entity;
    protected String dataTableId;
    protected String templatePath;
    protected String configViewName;

    private final DataTableUIService dataTableUIService;
    protected AbstractSimpleMetheaService simpleMetheaService;
    protected AbstractMetheaValidator validator;

    public AbstractSimpleMetheaController(DataTableUIService dataTableUIService) {
        this.dataTableUIService = dataTableUIService;
    }

    @GetMapping(value = StringUtils.EMPTY)
    public String viewAllWithFilter(Model model, B binder, F filter, Pagination pagination, HttpServletRequest request) {
        dataTableAttributes(model, binder, filter, pagination, request);
        return templatePath;
    }

    @PostMapping(value = SAVE_URL)
    public ModelAndView save(B binder, Model model, F filter, Pagination pagination, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        validator.validate(binder, errors);
        dataTableAttributes(model, binder, initFilter(), pagination, request);
        model.addAttribute("popup", dataTableId.concat("-add"));
        if (!CollectionUtils.isEmpty(errors)) {
            model.addAttribute("errors", errors);
            model.addAttribute("hasErrors", true);
            return new ModelAndView(templatePath);
        }
        simpleMetheaService.saveEntity(getEntityFromBinder(binder), binder);
        return new ModelAndView("redirect:" + ROOT_URL.concat(MetheaConstant.SLASH).concat(entity));
    }

    @PostMapping(value = MODIFY_URL)
    public ModelAndView modify(B binder, Model model, F filter, Pagination pagination, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("validateType", "EDIT");
        validator.validate(binder, errors);
        dataTableAttributes(model, binder, initFilter(), pagination, request);
        model.addAttribute("popup", dataTableId.concat("-edit"));
        errors.remove("validateType");
        if (!CollectionUtils.isEmpty(errors)) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", errors);
            return new ModelAndView(templatePath);
        }
        simpleMetheaService.modifyEntity(getEntityId(binder), binder);
        return new ModelAndView("redirect:" + ROOT_URL.concat(MetheaConstant.SLASH).concat(entity));
    }

    @ResponseBody
    @GetMapping(value = API_GET_BY_ID)
    public ResponseEntity<Map<String, Object>> getById(@RequestParam String id) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, String.format("Failed to get %s", entity));
        Object view = simpleMetheaService.getEntityViewById(id);
        if (!ObjectUtils.isEmpty(view)) {
            map.put(MetheaConstant.JSON_STATUS, 200);
            map.put(MetheaConstant.JSON_MESSAGE, String.format("Get %s success!", entity));
            map.put(entity, view);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = API_GET_ENTITY_BY_ID)
    public ResponseEntity<Map<String, Object>> getEntityById(@RequestParam String id) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, String.format("Failed to get %s", entity));
        Optional<E> o = simpleMetheaService.getEntityById(id);
        if (o.isPresent()) {
            map.put(MetheaConstant.JSON_STATUS, 200);
            map.put(MetheaConstant.JSON_MESSAGE, String.format("Get %s success!", entity));
            map.put(entity, o.get());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = API_ACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> activateEntity(@RequestBody Map<String, String> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, String.format("Failed to activate %s", entity));
        try {
            if (simpleMetheaService.activateEntity(payload.get("id"))) {
                map.put(MetheaConstant.JSON_STATUS, 200);
                map.put(MetheaConstant.JSON_MESSAGE, String.format("Activate %s success!", entity));
            }
        } catch (Exception ex) {
            map.put(MetheaConstant.JSON_MESSAGE, ex.getMessage());
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = API_DEACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> deactivateEntity(@RequestBody Map<String, String> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MetheaConstant.JSON_STATUS, 500);
        map.put(MetheaConstant.JSON_MESSAGE, String.format("Failed to deactivate %s", entity));
        try {
            if (simpleMetheaService.deactivateEntity(payload.get("id"))) {
                map.put(MetheaConstant.JSON_STATUS, 200);
                map.put(MetheaConstant.JSON_MESSAGE, String.format("Deactivate %s success!", entity));
            }
        } catch (Exception ex) {
            map.put(MetheaConstant.JSON_MESSAGE, ex.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    protected void dataTableAttributes(Model model, B binder, F filter, Pagination pagination, HttpServletRequest request) {
        if (CollectionUtils.isEmpty((List<?>) MetheaCache.CACHE_META_DATA.get(configViewName.concat(MetheaConstant.COLUMNS_KEY)))
                || CollectionUtils.isEmpty((List<?>) MetheaCache.CACHE_META_DATA.get(configViewName.concat(MetheaConstant.COLUMNS_LABEL)))) {
            dataTableUIService.getMetaTableConfiguration(configViewName);
        }

        Map<String, List<?>> map = new HashMap<>();
        map.put(MetheaConstant.JSON_DATA, simpleMetheaService.getAllEntityViewByFilter(getFilterColumns(filter), pagination));
        model.addAttribute(MetheaConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("tableHead", MetheaCache.CACHE_META_DATA.get(configViewName.concat(MetheaConstant.COLUMNS_LABEL)));
        model.addAttribute("tableColumns", MetheaCache.CACHE_META_DATA.get(configViewName.concat(MetheaConstant.COLUMNS_KEY)));
        model.addAttribute("tableFilterColumns", MetheaCache.CACHE_META_DATA.get(configViewName.concat(MetheaConstant.COLUMNS_FILTER)));
        model.addAttribute(entity, map);
        model.addAttribute("dataFilters", filter);
        model.addAttribute("pagination", pagination);
        model.addAttribute("binder", binder);
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute("hasErrors", false);
        model.addAttribute("popup", dataTableId);
        model.addAttribute(MetheaConstant.CORE_MENU, MetheaCache.CACHE_MENU.get(PrincipalUtils.getLoginGroupId(request)));
        getExtraAttribute(model);
    }

    protected F initFilter() {
        return null;
    }

    protected Map<String, Object> getFilterColumns(F filter) {
        return null;
    }

    protected E getEntityFromBinder(B binder) {
        return null;
    }

    protected String getEntityId(B binder) {
        return null;
    }

    protected Model getExtraAttribute(Model model) {
        return model;
    }
}
