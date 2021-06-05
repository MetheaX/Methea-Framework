package io.methea.repository;

import io.methea.domain.entity.TRole;
import io.methea.domain.view.RoleView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface RoleRepository extends CrudRepository<TRole, String>, HibernateExtensionRepository<RoleView, String> {
    TRole findTRoleByName(String name);
}
