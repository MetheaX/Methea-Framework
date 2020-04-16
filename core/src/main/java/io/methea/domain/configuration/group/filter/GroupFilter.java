package io.methea.domain.configuration.group.filter;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
public class GroupFilter {
    private String groupName = StringUtils.EMPTY;
    private String accountName = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;

    public GroupFilter() {
    }

    public GroupFilter(String groupName, String accountName, String status) {
        this.groupName = groupName;
        this.accountName = accountName;
        this.status = status;
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
