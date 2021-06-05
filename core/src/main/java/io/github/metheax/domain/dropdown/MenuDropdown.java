package io.github.metheax.domain.dropdown;

import io.github.metheax.domain.view.BaseView;
import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 11/07/2020
 */
@SelectFrom(fromClause = "FROM TMenu o", orderBy = "ORDER BY o.menuLabel ASC")
public class MenuDropdown extends BaseView<MenuDropdown> {
    private static final long serialVersionUID = 4856522925720018323L;
    @Column(name = "o.id")
    private String id;
    @Column(name = "o.menuLabel", key = "status", where = "AND o.status = :status AND o.parentId = 'P'", isLastColumn = true)
    private String name;

    public MenuDropdown(String id, String name) {
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
