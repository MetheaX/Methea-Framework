package io.github.metheax.repository;

import io.github.metheax.domain.entity.TGroup;
import io.github.metheax.domain.view.GroupAuthorityView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface GroupRepository extends CrudRepository<TGroup, String>, HibernateExtensionRepository<GroupAuthorityView, String> {
    List<TGroup> findAllByAccountId(String accountId);
}
