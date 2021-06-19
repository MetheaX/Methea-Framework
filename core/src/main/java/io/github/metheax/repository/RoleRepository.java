package io.github.metheax.repository;

import io.github.metheax.domain.entity.TRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface RoleRepository extends JpaRepository<TRole, String> {
    TRole findTRoleByName(String name);
}
