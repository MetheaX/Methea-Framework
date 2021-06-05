package io.github.metheax.repository;

import io.github.metheax.domain.entity.TRole;
import io.github.metheax.domain.view.RoleView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface RoleRepository extends CrudRepository<TRole, String>, HibernateExtensionRepository<RoleView, String> {
    TRole findTRoleByName(String name);
}
