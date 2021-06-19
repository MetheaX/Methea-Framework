package io.github.metheax.repository;

import io.github.metheax.domain.entity.TPermission;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : Kuylim Tith
 * Date : 15/09/2019
 */
public interface PermissionRepository extends JpaRepository<TPermission, String>,
        HibernateExtensionRepository<PermissionView, String> {
}
