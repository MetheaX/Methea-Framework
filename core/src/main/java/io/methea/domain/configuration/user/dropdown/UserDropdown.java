package io.methea.domain.configuration.user.dropdown;

import io.methea.domain.baseview.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 20/05/2020
 */
@SelectFrom(fromClause = "FROM TUser o", orderBy = "ORDER BY o.username ASC")
public class UserDropdown extends BaseView<UserDropdown> {
    private static final long serialVersionUID = -4166997484369313389L;
    @Column(name = "o.username")
    private String id;
    @Column(name = "o.username", key = "status", where = "AND o.status = :status", isLastColumn = true)
    private String name;

    public UserDropdown(String id, String name) {
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
