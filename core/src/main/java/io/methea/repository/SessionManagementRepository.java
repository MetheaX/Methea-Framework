package io.methea.repository;

import io.methea.domain.entity.TSessionManagement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionManagementRepository extends CrudRepository<TSessionManagement, String> {
    List<TSessionManagement> findAllByUserLoginIdAndIsLogout(String username, boolean isLogout);

    List<TSessionManagement> findAllByUserLoginIdAndSessionIdAndIsLogout(String username, String sessionId, boolean isLogout);
}
