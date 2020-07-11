package io.methea.controller.configuration.menu;

import io.methea.controller.abs.AbstractSimpleMetheaController;
import io.methea.domain.configuration.menu.dto.MenuBinder;
import io.methea.domain.configuration.menu.entity.TMenu;
import io.methea.domain.configuration.menu.filter.MenuFilter;
import io.methea.domain.configuration.menu.view.MenuView;
import io.methea.service.configuration.display.DataTableUIService;
import io.methea.service.configuration.menu.MMenuService;
import io.methea.validator.configuration.menu.MenuValidator;
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
 * Date : 11/07/2020
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping(value = {MMenuController.ROOT_URL})
public class MMenuController extends AbstractSimpleMetheaController<TMenu, MenuBinder, MenuView, MenuFilter> {

    static final String ROOT_URL = "/app/menu";

    @Inject
    public MMenuController(DataTableUIService dataTableUIService, MMenuService service, MenuValidator validator){
        super(dataTableUIService);
        super.validator = validator;
        super.metheaService = service;
        super.entity = "menu";
        super.dataTableId = "tbl-menu";
        configViewName = "menuList";
        templatePath = "configuration/menu/menu-list";
    }

    @Override
    protected MenuFilter initFilter(){
        return new MenuFilter();
    }

    @Override
    protected Map<String, Object> getFilterColumns(MenuFilter filter){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("menuLabel", "%".concat(filter.getMenuLabel().toLowerCase()).concat("%"));
        parameters.put("uriName", "%".concat(filter.getUriName().toLowerCase()).concat("%"));
        parameters.put("groupName", "%".concat(filter.getGroupName().toLowerCase()).concat("%"));
        parameters.put("status", "%".concat(StringUtils.isEmpty(filter.getStatus()) ? StringUtils.EMPTY :
                (filter.getStatus().substring(0, 1).toLowerCase())).concat("%"));
        return parameters;
    }

    @Override
    protected TMenu getEntityFromBinder(MenuBinder binder){
        TMenu menu = new TMenu();
        menu.setId(UUID.randomUUID().toString());
        return menu;
    }

    @Override
    protected String getEntityId(MenuBinder binder){
        return binder.getId();
    }
}