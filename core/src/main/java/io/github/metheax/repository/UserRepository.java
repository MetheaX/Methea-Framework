package io.github.metheax.repository;

import io.github.metheax.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername (String username);
    User findByUsernameAndStatus(String username, String status);
    List<User> findAllByGroupId(UUID groupId);
}
