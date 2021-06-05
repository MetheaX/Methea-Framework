package io.github.metheax.repository;

import io.github.metheax.domain.entity.TSessionManagement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionManagementRepository extends CrudRepository<TSessionManagement, String> {
    List<TSessionManagement> findAllByUserLoginIdAndIsLogout(String username, boolean isLogout);

    List<TSessionManagement> findAllByUserLoginIdAndSessionIdAndIsLogout(String username, String sessionId, boolean isLogout);
}
