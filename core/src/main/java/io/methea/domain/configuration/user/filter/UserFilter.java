package io.methea.domain.configuration.user.filter;

import io.methea.domain.common.filter.BaseFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
public class UserFilter extends BaseFilter<UserFilter> {
    private static final long serialVersionUID = 3824325380005821866L;

    private String groupName;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String status;

    public UserFilter(){
        this.groupName = StringUtils.EMPTY;
        this.username = StringUtils.EMPTY;
        this.firstName = StringUtils.EMPTY;
        this.lastName = StringUtils.EMPTY;
        this.phone = StringUtils.EMPTY;
        this.email = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public UserFilter(String groupName, String username, String firstName, String lastName, String phone, String email,
                      String status) {
        this.groupName = groupName;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
