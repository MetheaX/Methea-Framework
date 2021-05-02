package io.methea.repository.configuration.group;

import io.methea.domain.configuration.group.entity.TGroup;
import io.methea.domain.configuration.group.view.GroupAuthorityView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface UserGroupRepository extends CrudRepository<TGroup, String>, HibernateExtensionRepository<GroupAuthorityView, String> {
    List<TGroup> findAllByAccountId(String accountId);
}
