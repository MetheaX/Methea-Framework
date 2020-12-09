package io.methea.domain.configuration.user.dto;

import io.methea.domain.common.binder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
public class UserBinder extends BaseBinder<UserBinder> {
    private static final long serialVersionUID = 562319370498560845L;

    private String id = StringUtils.EMPTY;
    private String accountId = StringUtils.EMPTY;
    private String accountName = StringUtils.EMPTY;
    private String groupId = StringUtils.EMPTY;
    private String username = StringUtils.EMPTY;
    private String firstName = StringUtils.EMPTY;
    private String lastName = StringUtils.EMPTY;
    private String phone = StringUtils.EMPTY;
    private String email = StringUtils.EMPTY;
    private String password = StringUtils.EMPTY;
    private String confirmPassword = StringUtils.EMPTY;
    private String forceUserResetPassword = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getForceUserResetPassword() {
        return forceUserResetPassword;
    }

    public void setForceUserResetPassword(String forceUserResetPassword) {
        this.forceUserResetPassword = forceUserResetPassword;
    }
}
