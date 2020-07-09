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
    private String uriName;
    private String groupName;

    public MenuFilter() {
        this.menuLabel = StringUtils.EMPTY;
        this.uriName = StringUtils.EMPTY;
        this.groupName = StringUtils.EMPTY;
    }

    public MenuFilter(String menuLabel, String uriName, String groupName) {
        this.menuLabel = menuLabel;
        this.uriName = uriName;
        this.groupName = groupName;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
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
}