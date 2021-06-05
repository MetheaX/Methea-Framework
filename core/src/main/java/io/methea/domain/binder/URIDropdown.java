package io.methea.domain.binder;

import io.methea.domain.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@SelectFrom(fromClause = "FROM TMstURI o", orderBy = "ORDER BY o.uriName ASC")
public class URIDropdown extends BaseView<URIDropdown> {
    private static final long serialVersionUID = -1139917831592179706L;
    @Column(name = "o.id")
    private String id;
    @Column(name = "o.uriName", key = "status", where = "AND o.status = :status", isLastColumn = true)
    private String name;

    public URIDropdown(String id, String name) {
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
