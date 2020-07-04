package io.methea.domain.configuration.menu.dto;

import io.methea.domain.common.binder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
public class MenuBinder extends BaseBinder<MenuBinder> {
    private static final long serialVersionUID = 6718429442458624450L;

    private String id = StringUtils.EMPTY;
    private String parentId = StringUtils.EMPTY;
    private String menuLabel = StringUtils.EMPTY;
    private String menuIcon = StringUtils.EMPTY;
    private String uriId = StringUtils.EMPTY;
    private String uriName = StringUtils.EMPTY;
    private String groupId = StringUtils.EMPTY;
    private String groupName = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
