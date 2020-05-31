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
    @Query("DELETE FROM TUserPermission perm WHERE perm.userId = ?1 AND perm.viewId = ?2")
    void deleteExistingPerm(String username, String viewId);

    @Modifying
    @Query("DELETE FROM TUserPermission perm WHERE perm.userId = ?1")
    void deleteExistingPerm(String username);

    @Modifying
    @Query("DELETE FROM TUserPermission perm WHERE perm.viewId = ?1")
    void deleteExistingPermByView(String viewId);

    @Modifying
    @Query("DELETE FROM TUserPermission perm WHERE perm.roleUserId = ?1")
    void deleteExistingPermByRoleURIId(String roleURIId);

    @Modifying
    @Query("UPDATE TUserPermission perm SET perm.status = ?1 WHERE perm.roleUserId = ?2")
    void updateExistingPermURIBase(String status, String roleURIId);
}
