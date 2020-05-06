package io.methea.repository.configuration.uri;

import io.methea.domain.configuration.uri.entity.TRoleURI;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleUIRRepository extends CrudRepository<TRoleURI, String> {
    List<TRoleURI> findAllByRoleId(String roleId);
}
