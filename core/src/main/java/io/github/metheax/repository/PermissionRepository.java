package io.github.metheax.repository;

import io.github.metheax.domain.entity.TPermission;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface PermissionRepository extends CrudRepository<TPermission, String>,
        HibernateExtensionRepository<PermissionView, String> {
//    @Modifying
//    @Query("DELETE FROM TPermission perm WHERE perm.userId = ?1 AND perm.viewId = ?2")
//    void deleteExistingPerm(String username, String viewId);
//
//    @Modifying
//    @Query("DELETE FROM TPermission perm WHERE perm.userId = ?1")
//    void deleteExistingPerm(String username);
//
//    @Modifying
//    @Query("DELETE FROM TPermission perm WHERE perm.viewId = ?1")
//    void deleteExistingPermByView(String viewId);
//
//    @Modifying
//    @Query("DELETE FROM TPermission perm WHERE perm.roleUserId = ?1")
//    void deleteExistingPermByRoleURIId(String roleURIId);
//
//    @Modifying
//    @Query("UPDATE TPermission perm SET perm.status = ?1 WHERE perm.roleUserId = ?2")
//    void updateExistingPermURIBase(String status, String roleURIId);
}
