package io.methea.domain.configuration.role.dropdown;

import io.methea.domain.common.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@SelectFrom(fromClause = "FROM TRole o", orderBy = "ORDER BY o.name ASC")
public class RoleDropdown extends BaseView<RoleDropdown> {
    private static final long serialVersionUID = -1442247989855027751L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.name", key = "status", where = "AND o.status = :status", isLastColumn = true)
    private String name;

    public RoleDropdown(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
