package io.methea.repository.configuration.role;

import io.methea.domain.configuration.role.TRole;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface UserRoleRepository extends CrudRepository<TRole, String> {
}
