package io.github.metheax.repository;

import io.github.metheax.domain.entity.Permission;
import io.github.metheax.domain.view.PermissionView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 15/09/2019
 */
public interface PermissionRepository extends JpaRepository<Permission, UUID>,
        HibernateExtensionRepository<PermissionView, UUID> {
}
