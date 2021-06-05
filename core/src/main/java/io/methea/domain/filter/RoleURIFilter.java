package io.methea.domain.filter;

import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
public class RoleURIFilter extends BaseFilter<RoleURIFilter> {
    private static final long serialVersionUID = -6254104879027089658L;

    private String roleName;
    private String uriName;
    private String status;

    public RoleURIFilter() {
        this.roleName = StringUtils.EMPTY;
        this.uriName = StringUtils.EMPTY;
        this.status = StringUtils.EMPTY;
    }

    public RoleURIFilter(String roleName, String uriName, String status) {
        this.roleName = roleName;
        this.uriName = uriName;
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
