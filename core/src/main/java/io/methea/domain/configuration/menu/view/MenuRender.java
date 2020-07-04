package io.methea.domain.configuration.menu.view;

import io.methea.domain.common.view.BaseView;
import io.methea.domain.configuration.menu.entity.TMenu;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
public class MenuRender extends BaseView<MenuView> {
    private static final long serialVersionUID = -8238160367656173525L;
    private String menuLabel;
    private String menuIcon;

    private List<TMenu> subMenu;

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public List<TMenu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<TMenu> subMenu) {
        this.subMenu = subMenu;
    }
}
