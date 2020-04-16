package io.methea.domain.configuration.group.projection;

import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TUserGroup o, TAccount p", orderBy = "ORDER BY o.updatedDateTime DESC")
public class GroupProjection {

    @Column(name = "o.id")
    private String id;
    @Column(name = "o.groupName", key = "groupName", where = "AND LOWER(o.groupName) LIKE :groupName")
    private String groupName;
    @Column(name = "p.accountName", key = "accountName", where = "AND LOWER(p.accountName) LIKE :accountName")
    private String accountName;
    @Column(name = "o.status", key = "status", where = "AND LOWER(o.status) LIKE :status" +
            " AND o.accountId = p.id", isLastColumn = true)
    private String status;

    public GroupProjection(){}

    public GroupProjection(String id, String groupName, String accountName, String status) {
        this.id = id;
        this.groupName = groupName;
        this.accountName = accountName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
