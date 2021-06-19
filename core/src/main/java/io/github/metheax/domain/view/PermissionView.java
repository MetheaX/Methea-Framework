package io.github.metheax.domain.view;

import io.github.metheax.config.security.GrantedPermission;
import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;

/**
 * Author : Kuylim Tith
 * Date : 16/04/2020
 */
@SelectFrom(fromClause = "FROM TPermission o")
public class PermissionView extends BaseView<PermissionView> implements GrantedPermission {
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
