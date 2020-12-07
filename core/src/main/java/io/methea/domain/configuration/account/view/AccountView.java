package io.methea.domain.configuration.account.view;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */

import io.methea.domain.common.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

@SelectFrom(fromClause = "FROM TAccount o", orderBy = "ORDER BY o.accountName ASC")
public class AccountView extends BaseView<AccountView> {

    private static final long serialVersionUID = -8462411991270288224L;

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.accountName", key = "accountName", where = "AND LOWER(o.accountName) LIKE :accountName")
    private String accountName;
    @Column(name = "o.accountEmail", key = "accountEmail", where = "AND LOWER(o.accountEmail) LIKE :accountEmail")
    private String accountEmail;
    @Column(name = "o.accountAddress")
    private String accountAddress;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status", isLastColumn = true)
    private String status;

    public AccountView(String id, String accountName, String accountEmail, String accountAddress, String status) {
        this.id = id;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountAddress = accountAddress;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public String getStatus() {
        return status;
    }
}
