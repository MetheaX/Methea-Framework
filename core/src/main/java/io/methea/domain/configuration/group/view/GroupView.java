package io.methea.domain.configuration.group.view;

import io.methea.domain.common.view.BaseView;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TUserGroup o, TAccount p", join = "o.accountId = p.id",
        orderBy = "ORDER BY o.groupName ASC")
public class GroupView extends BaseView<GroupView> {
    private static final long serialVersionUID = -4838869017108743247L;
    @Column(name = "o.id")
    private String id;
    @Column(name = "o.groupName", key = "groupName", where = "AND LOWER(o.groupName) LIKE :groupName")
    private String groupName;
    @Column(name = "p.accountName", key = "accountName", where = "AND LOWER(p.accountName) LIKE :accountName")
    private String accountName;
    @Column(name = "o.accountId")
    private String accountId;
    @Column(name = "o.remarks")
    private String remarks;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status" +
            " AND o.accountId = p.id", isLastColumn = true)
    private String status;

    public GroupView(String id, String groupName, String accountName, String accountId, String remarks, String status) {
        this.id = id;
        this.groupName = groupName;
        this.accountName = accountName;
        this.accountId = accountId;
        this.remarks = remarks;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getStatus() {
        return status;
    }
}
