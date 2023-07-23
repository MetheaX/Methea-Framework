package io.github.metheax.repository;

import io.github.metheax.domain.entity.SessionManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionManagementRepository extends JpaRepository<SessionManagement, UUID> {
    List<SessionManagement> findAllByUserLoginIdAndIsLogout(String username, boolean isLogout);

    List<SessionManagement> findAllByUserLoginIdAndSessionIdAndIsLogout(String username, String sessionId, boolean isLogout);
}
