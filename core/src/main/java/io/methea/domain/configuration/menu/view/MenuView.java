package io.methea.domain.configuration.menu.view;

import io.methea.domain.common.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
@SelectFrom(fromClause = "FROM TMenu o", orderBy = "ORDER BY o.index ASC")
public class MenuView extends BaseView<MenuView> {
    private static final long serialVersionUID = -1518321161191263065L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.parentId")
    private String parentId;
    @Column(name = "o.menuLabel", key = "menuLabel", where = "AND LOWER(o.menuLabel) LIKE :menuLabel")
    private String menuLabel;
    @Column(name = "o.menuIcon", key = "menuIcon", where = "AND LOWER(o.menuIcon) LIKE :menuIcon")
    private String menuIcon;
    @Column(name = "o.uriId")
    private String uriId;
    @Column(name = "o.uriName", key = "uriName", where = "AND LOWER(o.uriName) LIKE :uriName")
    private String uriName;
    @Column(name = "o.groupId")
    private String groupId;
    @Column(name = "o.groupName", key = "groupName", where = "AND LOWER(o.groupName) LIKE :groupName")
    private String groupName;
    @Column(name = "o.index", key = "index", where = "AND o.index = :index")
    private Integer index;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public MenuView(String id, String parentId, String menuLabel, String menuIcon, String uriId, String uriName,
                    String groupId, String groupName, Integer index, String status) {
        this.id = id;
        this.parentId = parentId;
        this.menuLabel = menuLabel;
        this.menuIcon = menuIcon;
        this.uriId = uriId;
        this.uriName = uriName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.index = index;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
