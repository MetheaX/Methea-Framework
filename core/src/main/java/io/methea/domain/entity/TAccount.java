package io.methea.domain.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Entity
@Table(name = "core_account")
public class TAccount extends BaseEntity<TAccount> {

    private static final long serialVersionUID = -8120388696917673449L;

    @Id
    @Column(name = "account_id", length = 36)
    private String id;
    @Column(name = "account_code", nullable = false, unique = true, length = 18)
    private String accountCode;
    @Column(name = "account_name", nullable = false)
    private String accountName;
    @Column(name = "account_name_oth")
    private String accountNameOth;
    @Column(name = "account_email", nullable =  false)
    private String accountEmail;
    @Column(name = "account_address")
    private String accountAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNameOth() {
        return accountNameOth;
    }

    public void setAccountNameOth(String accountNameOth) {
        this.accountNameOth = accountNameOth;
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

    @Override
    public String toString() {
        return "TAccount{" +
                "id='" + id + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountAddress='" + accountAddress + '\'' +
                ", status='" + getStatus() + '\'' +
                ", creatUser='" + getCreatedUser() + '\'' +
                '}';
    }
}
