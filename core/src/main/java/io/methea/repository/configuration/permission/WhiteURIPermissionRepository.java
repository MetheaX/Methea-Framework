package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.entity.TPublicPermission;
import io.methea.domain.configuration.permission.view.WhiteURIPermissionView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WhiteURIPermissionRepository extends CrudRepository<TPublicPermission, String>,
        HibernateExtensionRepository<WhiteURIPermissionView, String> {
    List<TPublicPermission> findAllByStatus(String status);
}
