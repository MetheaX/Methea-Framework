package io.github.metheax.repository;

import io.github.metheax.domain.entity.TPublicPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiteURIPermissionRepository extends JpaRepository<TPublicPermission, String> {
    List<TPublicPermission> findAllByStatus(String status);
}
