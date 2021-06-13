package io.github.metheax.repository;

import io.github.metheax.domain.entity.TGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface GroupRepository extends CrudRepository<TGroup, String> {
    List<TGroup> findAllByAccountId(String accountId);
}
