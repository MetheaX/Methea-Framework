package io.github.metheax.service;

import io.github.metheax.domain.entity.TMenu;
import io.github.metheax.constant.MetheaConstant;
import io.github.metheax.domain.entity.TGroup;
import io.github.metheax.domain.binder.MenuBinder;
import io.github.metheax.domain.view.MenuRender;
import io.github.metheax.domain.view.MenuView;
import io.github.metheax.repository.MenuRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
@Service
public class MenuService extends AbstractSimpleMetheaService<TMenu, MenuBinder, String, MenuView> {

    private final MenuRepository repository;

    @Inject
    public MenuService(MenuRepository repository) {
        super(MenuView.class, repository, repository);
        this.repository = repository;
    }

    public Map<TGroup, List<MenuRender>> getAllMenuByGroup() {
        Map<TGroup, List<MenuRender>> map = new HashMap<>();
        List<TMenu> menus = repository.findAllByStatusOrderByIndexAsc(MetheaConstant.ACTIVE_STATUS);

        // group menu by group
        Map<TGroup, List<TMenu>> menuByGroup = menus.stream().collect(Collectors.groupingBy(TMenu::getGroup));
        List<MenuRender> menuRenders;
        // menu by parent
        for (Map.Entry<TGroup, List<TMenu>> entry : menuByGroup.entrySet()) {
            menuRenders = new ArrayList<>();
            Map<String, List<TMenu>> tmp = entry.getValue().stream().collect(Collectors.groupingBy(TMenu::getParentId));
            for (TMenu menu : entry.getValue()) {
                if (MetheaConstant.PARENT.equalsIgnoreCase(menu.getParentId())) {
                    MenuRender render = new MenuRender();
                    render.setMenuIcon(menu.getMenuIcon());
                    render.setMenuLabel(menu.getMenuLabel());
                    render.setSubMenu(tmp.get(menu.getId()));
                    menuRenders.add(render);
                }
            }
            map.put(entry.getKey(), menuRenders);
        }
        return map;
    }
}