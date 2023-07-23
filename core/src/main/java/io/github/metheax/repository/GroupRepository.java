package io.github.metheax.repository;

import io.github.metheax.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface GroupRepository extends JpaRepository<Group, UUID> {
    List<Group> findAllByAccountId(UUID accountId);
}
