package io.methea.domain.configuration.account.dropdown;

import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
@SelectFrom(fromClause = "FROM TAccount o", orderBy = "ORDER BY o.accountName ASC")
public class AccountDropdown {
    @Column(name = "o.id")
    private String id;
    @Column(name = "o.accountName", key = "status", where = "AND o.status = :status", isLastColumn = true)
    private String name;

    public AccountDropdown(String id, String name) {
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
