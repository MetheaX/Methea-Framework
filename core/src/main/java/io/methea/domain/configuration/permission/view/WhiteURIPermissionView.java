package io.methea.domain.configuration.permission.view;

import io.methea.domain.common.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

import java.util.List;

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
    private String methods;
    @Column(name = "ELEMENTS(o.allowedMethod)")
    private List<String> allowedMethod;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public WhiteURIPermissionView(String id, String uriId, String uriName, List<String> allowedMethod, String status) {
        this.id = id;
        this.uriId = uriId;
        this.uriName = uriName;
        this.allowedMethod = allowedMethod;
        this.status = status;
        this.methods = this.allowedMethod.toString();
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

    public String getMethods() {
        return methods;
    }

    public List<String> getAllowedMethod() {
        return allowedMethod;
    }

    public String getStatus() {
        return status;
    }
}
