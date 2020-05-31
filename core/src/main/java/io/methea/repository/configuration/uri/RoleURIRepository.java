package io.methea.repository.configuration.uri;

import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.uri.view.RoleURIView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleURIRepository extends CrudRepository<TRoleURI, String>,
        HibernateExtensionRepository<RoleURIView, String> {
    List<TRoleURI> findAllByRoleId(String roleId);

    List<TRoleURI> findAllByUriId(String uriId);

    @Modifying
    @Query("UPDATE TRoleURI o SET o.status = ?1 WHERE o.uriId = ?2")
    void updateExistingRoleURIBase(String status, String uriId);
}
