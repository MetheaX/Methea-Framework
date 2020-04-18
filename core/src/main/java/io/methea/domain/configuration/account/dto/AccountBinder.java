package io.methea.domain.configuration.account.dto;

import io.methea.domain.basebinder.BaseBinder;

/**
 * Author : DKSilverX
 * Date : 1/3/2020
 */
public class AccountBinder extends BaseBinder<AccountBinder> {

    private static final long serialVersionUID = -7725591983076415843L;
    private String id;
    private String accountName;
    private String accountEmail;
    private String accountAddress;
    private String accountCreatedUser;
    private String accountCreatedDate;
    private String accountUpdatedUser;
    private String accountUpdatedDate;
    private String status;

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

    @Override
    public String toString() {
        return "AccountBinder{" +
                "accountName='" + accountName + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountAddress='" + accountAddress + '\'' +
                ", accountCreatedUser='" + accountCreatedUser + '\'' +
                ", accountCreatedDate='" + accountCreatedDate + '\'' +
                ", accountUpdatedUser='" + accountUpdatedUser + '\'' +
                ", accountUpdatedDate='" + accountUpdatedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
