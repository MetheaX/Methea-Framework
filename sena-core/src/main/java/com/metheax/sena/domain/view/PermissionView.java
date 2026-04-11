package com.metheax.sena.domain.view;

import com.metheax.sena.config.security.GrantedPermission;
import com.metheax.sena.repository.hibernateextension.annotation.Column;
import com.metheax.sena.repository.hibernateextension.annotation.SelectFrom;

import java.io.Serial;
import java.io.Serializable;

/**
 * Author : Kuylim Tith
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM Permission o")
public class PermissionView implements GrantedPermission, Serializable {
    @Serial
    private static final long serialVersionUID = -3006282444338388548L;
    @Column(name = "o.resource.resourceName", key = "roles",
            where = "AND o.role IN :roles AND o.status = 'A'", isLastColumn = true)
    private String uriName;

    public PermissionView(String uriName) {
        this.uriName = uriName;
    }

    @Override
    public String getGrantedPermission() {
        return this.uriName;
    }
}
