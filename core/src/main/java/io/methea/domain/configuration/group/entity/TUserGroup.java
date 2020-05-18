package io.methea.domain.configuration.group.entity;

import io.methea.domain.baseentity.BaseEntity;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Entity
@Table(name = "tbl_core_group")
public class TUserGroup extends BaseEntity<TUserGroup> {
    private static final long serialVersionUID = -4855334223690873520L;
    @Id
    @Column(name = "group_id")
    private String id;
    @Column(name = "account_id", nullable = false)
    private String accountId;
    @Column(name = "group_name", nullable = false)
    private String groupName;
    @Column(name = "remarks")
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if (StringUtils.isEmpty(id)) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
