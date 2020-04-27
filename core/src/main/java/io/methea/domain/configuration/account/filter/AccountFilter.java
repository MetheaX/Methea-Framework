package io.methea.domain.configuration.account.filter;

import io.methea.domain.basefilter.AbstractMetheaFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
public class AccountFilter extends AbstractMetheaFilter<AccountFilter> {

    private static final long serialVersionUID = 6594832273463421285L;
    private String accountName;
    private String accountEmail;
    private String status;

    public AccountFilter(){
        this.accountName = StringUtils.EMPTY;
        this.accountEmail = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public AccountFilter(String accountName, String accountEmail, String status) {
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
