package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.entity.TRMUserPermission;
import io.methea.domain.configuration.permission.view.RMPermissionView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
public interface RMUserGrantedPermissionRepository extends CrudRepository<TRMUserPermission, String>,
        HibernateExtensionRepository<RMPermissionView, String> {
    List<TRMUserPermission> findAllByUserLoginId(String username);
    List<TRMUserPermission> findAllByRoleIdAndStatus(String roleId, String status);
}

