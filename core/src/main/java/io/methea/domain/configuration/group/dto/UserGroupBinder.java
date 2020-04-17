package io.methea.domain.configuration.group.dto;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
public class UserGroupBinder {
    private String id;
    private String accountId;
    private String groupName;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserGroupBinder{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}