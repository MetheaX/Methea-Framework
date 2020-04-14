package io.methea.domain.configuration.account.filter;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
public class AccountFilter {

    private String accountName;
    private String accountEmail;
    private String accountStatus;

    public AccountFilter(){}

    public AccountFilter(String accountName, String accountEmail, String accountStatus) {
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountStatus = accountStatus;
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
