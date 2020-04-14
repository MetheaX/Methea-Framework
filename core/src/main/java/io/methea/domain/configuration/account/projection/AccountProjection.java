package io.methea.domain.configuration.account.projection;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

@SelectFrom(fromClause = "FROM TAccount o")
public class AccountProjection {

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

    public AccountProjection(){}

    public AccountProjection(String id, String accountName, String accountEmail, String accountAddress, String status) {
        this.id = id;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.accountAddress = accountAddress;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
