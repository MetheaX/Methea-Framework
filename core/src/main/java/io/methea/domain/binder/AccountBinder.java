package io.methea.domain.binder;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
public class AccountBinder extends BaseBinder<AccountBinder> {

    private static final long serialVersionUID = -7725591983076415843L;
    private String id = StringUtils.EMPTY;
    private String accountName = StringUtils.EMPTY;
    private String accountEmail = StringUtils.EMPTY;
    private String accountAddress = StringUtils.EMPTY;
    private String accountCreatedUser = StringUtils.EMPTY;
    private String accountCreatedDate = StringUtils.EMPTY;
    private String accountUpdatedUser = StringUtils.EMPTY;
    private String accountUpdatedDate = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getAccountCreatedUser() {
        return accountCreatedUser;
    }

    public void setAccountCreatedUser(String accountCreatedUser) {
        this.accountCreatedUser = accountCreatedUser;
    }

    public String getAccountUpdatedUser() {
        return accountUpdatedUser;
    }

    public void setAccountUpdatedUser(String accountUpdatedUser) {
        this.accountUpdatedUser = accountUpdatedUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(String accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public String getAccountUpdatedDate() {
        return accountUpdatedDate;
    }

    public void setAccountUpdatedDate(String accountUpdatedDate) {
        this.accountUpdatedDate = accountUpdatedDate;
    }
}
