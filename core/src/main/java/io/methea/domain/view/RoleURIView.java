package io.methea.domain.view;

import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@SelectFrom(fromClause = "FROM TRoleURI o, TRole p, TMstURI q", join = "o.roleId = p.id AND o.uriId = q.id",
        orderBy = "ORDER BY o.uriName ASC")
public class RoleURIView extends BaseView<RoleURIView> {
    private static final long serialVersionUID = -8338954022243292523L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "p.id")
    private String roleId;
    @Column(name = "p.name", key = "roleName", where = "AND LOWER(p.name) LIKE :roleName")
    private String roleName;
    @Column(name = "q.id")
    private String uriId;
    @Column(name = "q.uriName", key = "uriName", where = "AND LOWER(q.uriName) LIKE :uriName")
    private String uriName;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status " +
            "AND o.roleId = p.id AND o.uriId = q.id", isLastColumn = true)
    private String status;

    public RoleURIView(String id, String roleId, String roleName, String uriId, String uriName, String status) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.uriId = uriId;
        this.uriName = uriName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
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
