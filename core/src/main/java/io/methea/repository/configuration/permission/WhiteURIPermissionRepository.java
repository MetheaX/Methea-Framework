package io.methea.repository.configuration.permission;

import io.methea.domain.configuration.permission.entity.TWhiteURIPermission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WhiteURIPermissionRepository extends CrudRepository<TWhiteURIPermission, String> {
    List<TWhiteURIPermission> findAllByStatus(String status);
}
