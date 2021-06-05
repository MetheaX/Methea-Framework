package io.methea.domain.view;

import io.methea.config.security.GrantedPermission;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TUserPermission o")
public class PermissionView extends BaseView<PermissionView> implements GrantedPermission {
    private static final long serialVersionUID = -3006282444338388548L;
    @Column(name = "o.uriName", key = "username", where = "AND o.userId = :username AND o.status = 'A'", isLastColumn = true)
    private String uriName;

    public PermissionView(String uriName) {
        this.uriName = uriName;
    }

    @Override
    public String getGrantedPermission() {
        return this.uriName;
    }
}
