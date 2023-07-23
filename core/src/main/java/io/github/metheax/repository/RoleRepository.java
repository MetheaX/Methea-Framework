package io.github.metheax.repository;

import io.github.metheax.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findTRoleByName(String name);
}
