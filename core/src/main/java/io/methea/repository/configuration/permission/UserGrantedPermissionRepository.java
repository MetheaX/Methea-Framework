package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.entity.TUserPermission;
import io.methea.domain.configuration.permission.view.PermissionView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface UserGrantedPermissionRepository extends CrudRepository<TUserPermission, String>,
        HibernateExtensionRepository<PermissionView, String> {
    @Modifying
    @Query("DELETE FROM TUserPermission perm WHERE perm.userId = ?1")
    void deleteExistingPerm(String username);
}
