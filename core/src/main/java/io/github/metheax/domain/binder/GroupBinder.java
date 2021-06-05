package io.github.metheax.domain.binder;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
public class GroupBinder extends BaseBinder<GroupBinder> {
    private static final long serialVersionUID = -7486953695198367520L;
    private String id = StringUtils.EMPTY;
    private String accountId = StringUtils.EMPTY;
    private String groupName = StringUtils.EMPTY;
    private String remarks = StringUtils.EMPTY;

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
