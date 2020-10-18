package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.entity.TWhiteURIPermission;
import io.methea.domain.configuration.permission.view.WhiteURIPermissionView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WhiteURIPermissionRepository extends CrudRepository<TWhiteURIPermission, String>,
        HibernateExtensionRepository<WhiteURIPermissionView, String> {
    List<TWhiteURIPermission> findAllByStatus(String status);
}
