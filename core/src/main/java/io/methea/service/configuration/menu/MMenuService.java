package io.methea.service.configuration.menu;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.menu.dto.MenuBinder;
import io.methea.domain.configuration.menu.entity.TMenu;
import io.methea.domain.configuration.menu.view.MenuRender;
import io.methea.domain.configuration.menu.view.MenuView;
import io.methea.repository.configuration.menu.MenuRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
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
public class MMenuService extends AbstractSimpleMetheaService<TMenu, MenuBinder, String, MenuView> {

    private final MenuRepository repository;

    @Inject
    public MMenuService(MenuRepository repository) {
        super(MenuView.class, repository, repository);
        this.repository = repository;
    }

    public Map<String, List<MenuRender>> getAllMenuByGroup() {
        Map<String, List<MenuRender>> map = new HashMap<>();
        List<TMenu> menus = repository.findAllByStatusOrderByIndexAsc(MConstant.ACTIVE_STATUS);

        // group menu by group
        Map<String, List<TMenu>> menuByGroup = menus.stream().collect(Collectors.groupingBy(TMenu::getGroupId));
        List<MenuRender> menuRenders;
        // menu by parent
        for (Map.Entry<String, List<TMenu>> entry : menuByGroup.entrySet()) {
            menuRenders = new ArrayList<>();
            Map<String, List<TMenu>> tmp = entry.getValue().stream().collect(Collectors.groupingBy(TMenu::getParentId));
            for (TMenu menu : entry.getValue()) {
                if (MConstant.PARENT.equalsIgnoreCase(menu.getParentId())) {
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
