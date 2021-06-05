package io.github.metheax.domain.view;

import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@SelectFrom(fromClause = "FROM APIBase o", orderBy = "ORDER BY o.updatedDateTime DESC")
public class APIBaseView extends BaseView<APIBaseView> {
    private static final long serialVersionUID = 4898042790496636844L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.apiUrl" , key = "apiUrl", where = "AND LOWER(o.apiUrl) LIKE :apiUrl")
    private String apiUrl;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public APIBaseView(String id, String apiUrl, String status) {
        this.id = id;
        this.apiUrl = apiUrl;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
