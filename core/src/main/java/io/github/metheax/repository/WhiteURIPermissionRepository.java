package io.github.metheax.repository;

import io.github.metheax.domain.entity.PublicPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WhiteURIPermissionRepository extends JpaRepository<PublicPermission, UUID> {
    List<PublicPermission> findAllByStatus(String status);
}
