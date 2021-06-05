package io.methea.domain.filter;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
public class RMPermissionFilter extends BaseFilter<RMPermissionFilter> {
    private static final long serialVersionUID = 1984470620365909355L;

    private String userLoginId;
    private String firstName;
    private String lastName;
    private String roleName;
    private String status;

    public RMPermissionFilter() {
        this.userLoginId = StringUtils.EMPTY;
        this.firstName = StringUtils.EMPTY;
        this.lastName = StringUtils.EMPTY;
        this.roleName = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public RMPermissionFilter(String userLoginId, String firstName, String lastName, String roleName, String status) {
        this.userLoginId = userLoginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
        this.status = status;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
