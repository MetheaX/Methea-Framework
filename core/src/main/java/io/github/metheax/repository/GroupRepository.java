package io.github.metheax.repository;

import io.github.metheax.domain.entity.TGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface GroupRepository extends JpaRepository<TGroup, String> {
    List<TGroup> findAllByAccountId(String accountId);
}
