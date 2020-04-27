package io.methea.domain.configuration.group.filter;

import io.methea.domain.basefilter.AbstractMetheaFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
public class GroupFilter extends AbstractMetheaFilter<GroupFilter> {
    private static final long serialVersionUID = -8789027105206740582L;
    private String groupName;
    private String accountName;
    private String status;

    public GroupFilter() {
        this.groupName = StringUtils.EMPTY;
        this.accountName = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
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
