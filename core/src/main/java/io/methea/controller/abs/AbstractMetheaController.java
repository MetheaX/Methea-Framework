package io.methea.controller.abs;

import io.methea.cache.MCache;
import io.methea.constant.MConstant;
import io.methea.domain.basebinder.abs.AbstractMetheaBinder;
import io.methea.domain.baseentity.abs.AbstractMetheaEntity;
import io.methea.domain.basefilter.AbstractMetheaFilter;
import io.methea.domain.baseview.abs.AbstractMetheaView;
import io.methea.service.abs.AbstractMetheaService;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.utils.Pagination;
import io.methea.utils.SystemUtils;
import io.methea.validator.abs.AbstractMetheaValidator;
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

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public abstract class AbstractMetheaController<E extends AbstractMetheaEntity<E>, B extends AbstractMetheaBinder<B>,
        V extends AbstractMetheaView<V>, F extends AbstractMetheaFilter<F>> {
    protected static final String ROOT_URL = "/app";
    private static final String SAVE_URL = "/save";
    private static final String MODIFY_URL = "/modify";
    private static final String API_GET_BY_ID = "/rest";
    private static final String API_ACTIVATE_URL = "/rest/activate";
    private static final String API_DEACTIVATE_URL = "/rest/deactivate";

    protected String entity;
    protected String dataTableId;
    protected String templatePath;
    protected String configViewName;

    private final DataTableUIService dataTableUIService;
    protected AbstractMetheaService metheaService;
    protected AbstractMetheaValidator validator;

    public AbstractMetheaController(DataTableUIService dataTableUIService) {
        this.dataTableUIService = dataTableUIService;
    }

    @RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
    public String viewAllWithFilter(Model model, B binder, F filter, Pagination pagination, HttpServletRequest request) {
        dataTableAttributes(model, binder, filter, pagination, request);
        return templatePath;
    }

    @RequestMapping(value = SAVE_URL, method = RequestMethod.POST)
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
        metheaService.saveEntity(getEntityFromBinder(binder), binder);
        return new ModelAndView("redirect:" + ROOT_URL.concat(MConstant.SLASH).concat(entity));
    }

    @RequestMapping(value = MODIFY_URL, method = RequestMethod.POST)
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
        metheaService.modifyEntity(getEntityId(binder), binder);
        return new ModelAndView("redirect:" + ROOT_URL.concat(MConstant.SLASH).concat(entity));
    }

    @ResponseBody
    @GetMapping(value = API_GET_BY_ID)
    public ResponseEntity<Map<String, Object>> getById(@RequestParam String id) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, String.format("Failed to get %s", entity));
        Object view = metheaService.getEntityViewById(id);
        if (!ObjectUtils.isEmpty(view)) {
            map.put(MConstant.JSON_STATUS, 200);
            map.put(entity, view);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = API_ACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> activateEntity(@RequestBody Map<String, String> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, String.format("Failed to activate %s", entity));
        if (metheaService.activateEntity(payload.get("id"))) {
            map.put(MConstant.JSON_STATUS, 200);
            map.put(MConstant.JSON_MESSAGE, String.format("Activate %s success!", entity));
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = API_DEACTIVATE_URL)
    public ResponseEntity<Map<String, Object>> deactivateEntity(@RequestBody Map<String, String> payload) {
        Map<String, Object> map = new HashMap<>();
        map.put(MConstant.JSON_STATUS, 500);
        map.put(MConstant.JSON_MESSAGE, String.format("Failed to deactivate %s", entity));
        if (metheaService.deactivateEntity(payload.get("id"))) {
            map.put(MConstant.JSON_STATUS, 200);
            map.put(MConstant.JSON_MESSAGE, String.format("Deactivate %s success!", entity));
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    protected void dataTableAttributes(Model model, B binder, F filter, Pagination pagination, HttpServletRequest request) {
        if (CollectionUtils.isEmpty((List<?>) MCache.cacheMetaData.get(configViewName.concat(MConstant.COLUMNS_KEY)))
                || CollectionUtils.isEmpty((List<?>) MCache.cacheMetaData.get(configViewName.concat(MConstant.COLUMNS_LABEL)))) {
            dataTableUIService.getMetaTableConfiguration(configViewName);
        }

        Map<String, List<?>> map = new HashMap<>();
        map.put(MConstant.JSON_DATA, metheaService.getAllEntityViewByFilter(getFilterColumns(filter), pagination));
        model.addAttribute(MConstant.CONTEXT_PATH_KEY, SystemUtils.getBaseUrl(request));
        model.addAttribute("tableHead", MCache.cacheMetaData.get(configViewName.concat(MConstant.COLUMNS_LABEL)));
        model.addAttribute("tableColumns", MCache.cacheMetaData.get(configViewName.concat(MConstant.COLUMNS_KEY)));
        model.addAttribute("tableFilterColumns", MCache.cacheMetaData.get(configViewName.concat(MConstant.COLUMNS_FILTER)));
        model.addAttribute(entity, map);
        model.addAttribute("dataFilters", filter);
        model.addAttribute("pagination", pagination);
        model.addAttribute("binder", binder);
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute("hasErrors", false);
        model.addAttribute("popup", dataTableId);
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
