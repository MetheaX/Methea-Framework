package io.methea.repository.configuration.user;

import io.methea.domain.configuration.user.entity.TUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface UserRepository extends CrudRepository<TUser, String> {
    TUser findByUsername (String username);
}
