package io.methea.repository;

import io.methea.domain.entity.TGroup;
import io.methea.domain.view.GroupAuthorityView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface GroupRepository extends CrudRepository<TGroup, String>, HibernateExtensionRepository<GroupAuthorityView, String> {
    List<TGroup> findAllByAccountId(String accountId);
}
