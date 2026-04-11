package com.metheax.sena.repository;

import com.metheax.sena.domain.entity.Permission;
import com.metheax.sena.domain.view.PermissionView;
import com.metheax.sena.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 15/09/2019
 */
public interface PermissionRepository extends JpaRepository<Permission, UUID>,
        HibernateExtensionRepository<PermissionView, UUID> {
}
