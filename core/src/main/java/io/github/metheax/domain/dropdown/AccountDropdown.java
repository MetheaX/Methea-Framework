package io.github.metheax.domain.dropdown;

import io.github.metheax.domain.view.BaseView;
import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
@SelectFrom(fromClause = "FROM TAccount o", orderBy = "ORDER BY o.accountName ASC")
public class AccountDropdown extends BaseView<AccountDropdown> {
    private static final long serialVersionUID = 5519154975427265865L;
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
