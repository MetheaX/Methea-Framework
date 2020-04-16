package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.entity.TUserPermission;
import io.methea.domain.configuration.permission.projection.PermissionProjection;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface UserGrantedPermissionRepository extends CrudRepository<TUserPermission, String>,
        HibernateExtensionRepository<PermissionProjection> {
}
