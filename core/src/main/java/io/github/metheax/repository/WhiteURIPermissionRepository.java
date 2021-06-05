package io.github.metheax.repository;

import io.github.metheax.domain.entity.TPublicPermission;
import io.github.metheax.domain.view.WhiteURIPermissionView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WhiteURIPermissionRepository extends CrudRepository<TPublicPermission, String>,
        HibernateExtensionRepository<WhiteURIPermissionView, String> {
    List<TPublicPermission> findAllByStatus(String status);
}
