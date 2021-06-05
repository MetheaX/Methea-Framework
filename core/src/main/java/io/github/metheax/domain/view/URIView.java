package io.github.metheax.domain.view;

import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@SelectFrom(fromClause = "FROM TMstURI o", orderBy = "ORDER BY o.uriName ASC")
public class URIView extends BaseView<URIView> {
    private static final long serialVersionUID = 5717744242304408285L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.uriName", key = "uriName", where = "AND LOWER(o.uriName) LIKE :uriName")
    private String uriName;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public URIView(String id, String uriName, String status) {
        this.id = id;
        this.uriName = uriName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
