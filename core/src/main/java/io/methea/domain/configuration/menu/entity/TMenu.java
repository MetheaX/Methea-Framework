package io.methea.domain.configuration.menu.entity;

import io.methea.domain.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author : DKSilverX
 * Date : 28/06/2020
 */
@Entity
@Table(name = "tbl_core_menu")
public class TMenu extends BaseEntity<TMenu> {

    private static final long serialVersionUID = 8047293537806520907L;

    @Id
    @Column(name = "menu_id")
    private String id;
    @Column(name = "parent_menu")
    private String parentId;
    @Column(name = "menu_label", nullable = false)
    private String menuLabel;
    @Column(name = "menu_icon", nullable = false)
    private String menuIcon;
    @Column(name = "uri_id", nullable = false)
    private String uriId;
    @Column(name = "uri_name", nullable = false)
    private String uriName;
    @Column(name = "group_id", nullable = false)
    private String groupId;
    @Column(name = "group_name", nullable = false)
    private String groupName;
    @Column(name = "index")
    private Integer index;

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
