package io.github.metheax.domain.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.UUID;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@Entity
@Table(name = "core_group")
public class TGroup extends BaseEntity<TGroup> {
    private static final long serialVersionUID = -4855334223690873520L;
    @Id
    @Column(name = "group_id", length = 36)
    private String id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private TAccount account;
    @Column(name = "group_code", nullable = false, unique = true, length = 18)
    private String groupCode;
    @Column(name = "group_name", nullable = false)
    private String groupName;
    @Column(name = "group_name_oth")
    private String groupNameOth;
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

    public TAccount getAccount() {
        return account;
    }

    public void setAccount(TAccount account) {
        this.account = account;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNameOth() {
        return groupNameOth;
    }

    public void setGroupNameOth(String groupNameOth) {
        this.groupNameOth = groupNameOth;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
