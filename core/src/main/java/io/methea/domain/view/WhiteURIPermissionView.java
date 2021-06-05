package io.methea.domain.view;

import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 17/10/2020
 */
@SelectFrom(fromClause = "FROM TWhiteURIPermission o")
public class WhiteURIPermissionView extends BaseView<WhiteURIPermissionView> {
    private static final long serialVersionUID = 2047446823715281637L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.uriId")
    private String uriId;
    @Column(name = "o.uriName", key = "uriName", where = "AND LOWER(o.uriName) LIKE :uriName")
    private String uriName;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public WhiteURIPermissionView(String id, String uriId, String uriName, String status) {
        this.id = id;
        this.uriId = uriId;
        this.uriName = uriName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUriId() {
        return uriId;
    }

    public String getUriName() {
        return uriName;
    }

    public String getStatus() {
        return status;
    }
}
