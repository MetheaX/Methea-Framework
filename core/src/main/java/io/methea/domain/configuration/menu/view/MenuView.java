package io.methea.domain.configuration.menu.view;

import io.methea.domain.common.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 04/07/2020
 */
@SelectFrom(fromClause = "FROM TMenu o", orderBy = "ORDER BY o.updatedDateTime DESC")
public class MenuView extends BaseView<MenuView> {
    private static final long serialVersionUID = -1518321161191263065L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.menuLabel", key = "menuLabel", where = "AND LOWER(o.menuLabel) LIKE :menuLabel")
    private String menuLabel;
    @Column(name = "o.uriName", key = "uriName", where = "AND LOWER(o.uriName) LIKE :uriName")
    private String uriName;
    @Column(name = "o.groupName", key = "groupName", where = "AND LOWER(o.groupName) LIKE :groupName")
    private String groupName;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public MenuView(String id, String menuLabel, String uriName, String groupName, String status) {
        this.id = id;
        this.menuLabel = menuLabel;
        this.uriName = uriName;
        this.groupName = groupName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
