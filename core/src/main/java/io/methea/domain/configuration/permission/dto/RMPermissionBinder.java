package io.methea.domain.configuration.permission.dto;

import io.methea.domain.basebinder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
public class RMPermissionBinder extends BaseBinder<RMPermissionBinder> {
    private static final long serialVersionUID = 9055190403503896177L;

    private String id = StringUtils.EMPTY;
    private String userId = StringUtils.EMPTY;
    private String userLoginId = StringUtils.EMPTY;
    private String firstName = StringUtils.EMPTY;
    private String lastName = StringUtils.EMPTY;
    private String roleId = StringUtils.EMPTY;
    private String roleName = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
