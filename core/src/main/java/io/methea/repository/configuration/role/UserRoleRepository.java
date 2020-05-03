package io.methea.repository.configuration.role;

import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.role.view.RoleView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface UserRoleRepository extends CrudRepository<TRole, String>, HibernateExtensionRepository<RoleView, String> {
    TRole findTRoleByName(String name);
}
