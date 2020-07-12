package io.methea.domain.configuration.menu.filter;

import io.methea.domain.common.filter.BaseFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
public class MenuFilter extends BaseFilter<MenuFilter> {
    private static final long serialVersionUID = 5170699665018789972L;

    private String menuLabel;
    private String menuIcon;
    private String uriName;
    private String groupName;
    private Integer index;
    private String status;

    public MenuFilter() {
        this.menuLabel = StringUtils.EMPTY;
        this.menuIcon = StringUtils.EMPTY;
        this.uriName = StringUtils.EMPTY;
        this.groupName = StringUtils.EMPTY;
        this.index = 0;
        this.status = StringUtils.EMPTY;
    }

    public MenuFilter(String menuLabel, String menuIcon, String uriName, String groupName, Integer index, String status) {
        this.menuLabel = menuLabel;
        this.menuIcon = menuIcon;
        this.uriName = uriName;
        this.groupName = groupName;
        this.index = index;
        this.status = status;
    }

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

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
