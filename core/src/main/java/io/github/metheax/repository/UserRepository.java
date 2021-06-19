package io.github.metheax.repository;

import io.github.metheax.domain.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface UserRepository extends JpaRepository<TUser, String> {
    TUser findByUsername (String username);
    TUser findByUsernameAndStatus(String username, String status);
    List<TUser> findAllByGroupId(String groupId);
}
