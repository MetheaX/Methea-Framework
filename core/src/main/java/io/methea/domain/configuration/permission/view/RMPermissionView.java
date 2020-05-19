package io.methea.domain.configuration.permission.view;

import io.methea.domain.baseview.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
@SelectFrom(fromClause = "FROM TRMUserPermission o")
public class RMPermissionView extends BaseView<RMPermissionView> {
    private static final long serialVersionUID = -6385779119846969846L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.userLoginId", key = "username", where = "AND LOWER(o.userLoginId) LIKE :username")
    private String userLoginId;
    @Column(name = "o.firstName", key = "firstName", where = "AND LOWER(o.firstName) LIKE :firstName")
    private String firstName;
    @Column(name = "o.lastName", key = "lastName", where = "AND LOWER(o.lastName) LIKE :lastName")
    private String lastName;
    @Column(name = "o.roleId")
    private String roleId;
    @Column(name = "o.roleName", key = "roleName", where = "AND LOWER(o.roleName) LIKE :roleName")
    private String roleName;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public RMPermissionView(String id, String userLoginId, String firstName, String lastName, String roleId,
                            String roleName, String status) {
        this.id = id;
        this.userLoginId = userLoginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getStatus() {
        return status;
    }
}
