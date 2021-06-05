package io.github.metheax.web.controller;

import io.github.metheax.cache.MetheaCache;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.controller.AbstractSimpleMetheaController;
import io.github.metheax.domain.entity.TMenu;
import io.github.metheax.service.DataTableUIService;
import io.github.metheax.service.DropdownService;
import io.github.metheax.service.MenuService;
import io.github.metheax.domain.binder.MenuBinder;
import io.github.metheax.domain.filter.MenuFilter;
import io.github.metheax.domain.view.MenuView;
import io.github.metheax.validator.MenuValidator;
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
 * Date : 11/07/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {MenuController.ROOT_URL})
public class MenuController extends AbstractSimpleMetheaController<TMenu, MenuBinder, MenuView, MenuFilter> {

    static final String ROOT_URL = "/app/menu";
    private final DropdownService dropdownService;

    @Inject
    public MenuController(DataTableUIService dataTableUIService, DropdownService dropdownService,
                          MenuService service, MenuValidator validator) {
        super(dataTableUIService);
        this.dropdownService = dropdownService;
        super.validator = validator;
        super.simpleMetheaService = service;
        super.entity = "menu";
        super.dataTableId = "tbl-menu";
        configViewName = "menuList";
        templatePath = "configuration/menu/menu-list";
    }

    @Override
    protected Model getExtraAttribute(Model model) {
        dropdownService.refreshMenuDropdown();
        model.addAttribute(MetheaConstant.DROPDOWN, MetheaCache.CACHE_META_DATA.get(MetheaConstant.DROPDOWN));
        return model;
    }

    @Override
    protected MenuFilter initFilter() {
        return new MenuFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(MenuFilter filter) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("menuLabel", "%".concat(filter.getMenuLabel().toLowerCase()).concat("%"));
        parameters.put("uriName", "%".concat(filter.getUriName().toLowerCase()).concat("%"));
        parameters.put("menuIcon", "%".concat(filter.getMenuIcon().toLowerCase()).concat("%"));
        parameters.put("groupName", "%".concat(filter.getGroupName().toLowerCase()).concat("%"));
        if (filter.getIndex() != 0) {
            parameters.put("index", filter.getIndex());
        }

        parameters.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return parameters;
    }

    @Override
    protected TMenu getEntityFromBinder(MenuBinder binder) {
        TMenu menu = new TMenu();
        menu.setId(UUID.randomUUID().toString());
        return menu;
    }

    @Override
    protected String getEntityId(MenuBinder binder) {
        return binder.getId();
    }
}
