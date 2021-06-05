package io.github.metheax.repository;

import io.github.metheax.domain.entity.TUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface UserRepository extends CrudRepository<TUser, String> {
    TUser findByUsername (String username);
    TUser findByUsernameAndStatus(String username, String status);
    List<TUser> findAllByGroupId(String groupId);
}
