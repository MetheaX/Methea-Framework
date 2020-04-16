package io.methea.domain.configuration.permission.projection;

import io.methea.config.security.domain.GrantedPermission;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TUserPermission o")
public class PermissionProjection implements GrantedPermission {
    @Column(name = "o.uriName", key = "username", where = "AND o.userId = :username", isLastColumn = true)
    private String uriName;

    public PermissionProjection(String uriName) {
        this.uriName = uriName;
    }

    @Override
    public String getGrantedPermission() {
        return this.uriName;
    }
}
