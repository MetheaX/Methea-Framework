package io.methea.domain.configuration.account.entity;

import io.methea.domain.baseentity.BaseEntity;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Entity
@Table(name = "tbl_core_account")
public class TAccount extends BaseEntity {
    @Id
    @Column(name = "account_id")
    private String id;
    private String accountName;
    private String accountEmail;
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
