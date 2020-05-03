package io.methea.domain.configuration.role.view;

import io.methea.domain.baseview.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@SelectFrom(fromClause = "FROM TRole o", orderBy = "ORDER BY o.updatedDateTime DESC")
public class RoleView extends BaseView<RoleView> {
    private static final long serialVersionUID = 1983737063764391023L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.name", key = "name", where = "AND LOWER(o.name) LIKE :name")
    private String name;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public RoleView(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
