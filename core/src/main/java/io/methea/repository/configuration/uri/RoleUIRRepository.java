package io.methea.repository.configuration.uri;

import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.uri.view.RoleURIView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleUIRRepository extends CrudRepository<TRoleURI, String>,
        HibernateExtensionRepository<RoleURIView, String> {
    List<TRoleURI> findAllByRoleId(String roleId);
}
