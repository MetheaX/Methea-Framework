package io.methea.domain.configuration.resource.dto;

import io.methea.domain.common.binder.BaseBinder;
import org.apache.commons.lang3.StringUtils;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
public class RoleURIBinder extends BaseBinder<RoleURIBinder> {
    private static final long serialVersionUID = 6729298896564464429L;

    private String id = StringUtils.EMPTY;
    private String roleId = StringUtils.EMPTY;
    private String uriId = StringUtils.EMPTY;
    private String uriName = StringUtils.EMPTY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }
}
