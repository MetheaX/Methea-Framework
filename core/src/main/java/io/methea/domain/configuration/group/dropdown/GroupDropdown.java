package io.methea.domain.configuration.group.dropdown;

import io.methea.domain.baseview.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 25/04/2020
 */
@SelectFrom(fromClause = "FROM TUserGroup o", orderBy = "ORDER BY o.groupName ASC")
public class GroupDropdown extends BaseView<GroupDropdown> {
    private static final long serialVersionUID = -8990771004017469895L;
    @Column(name = "o.id")
    private String id;
    @Column(name = "o.groupName", key = "status", where = "AND o.status = :status", isLastColumn = true)
    private String name;

    public GroupDropdown(String id, String name) {
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
