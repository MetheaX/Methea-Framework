package io.methea.repository.configuration.jwt;

import io.methea.domain.configuration.jwt.entity.TSessionManagement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionManagementRepository extends CrudRepository<TSessionManagement, String> {
    List<TSessionManagement> findAllByUserLoginIdAndIsLogout(String username, boolean isLogout);
}
