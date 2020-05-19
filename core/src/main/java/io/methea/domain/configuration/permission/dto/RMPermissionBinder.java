package io.methea.domain.configuration.permission.dto;

import io.methea.domain.basebinder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
public class RMPermissionBinder extends BaseBinder<RMPermissionBinder> {
    private static final long serialVersionUID = 9055190403503896177L;

    private String id;
    private String userLoginId;
    private String firstName;
    private String lastName;
    private String roleId;
    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
