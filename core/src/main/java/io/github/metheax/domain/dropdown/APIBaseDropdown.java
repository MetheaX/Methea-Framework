package io.github.metheax.domain.dropdown;

import io.github.metheax.domain.view.BaseView;
import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@SelectFrom(fromClause = "FROM APIBase o", orderBy = "ORDER BY o.apiUrl ASC")
public class APIBaseDropdown extends BaseView<APIBaseDropdown> {
    private static final long serialVersionUID = 8746034437533790058L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.apiUrl", key = "status", where = "AND o.status = :status", isLastColumn = true)
    private String name;

    public APIBaseDropdown(String id, String name) {
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