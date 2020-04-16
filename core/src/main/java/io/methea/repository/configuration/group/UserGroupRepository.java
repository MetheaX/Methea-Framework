package io.methea.repository.configuration.group;

import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.group.projection.GroupProjection;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
public interface UserGroupRepository extends CrudRepository<TUserGroup, String>, HibernateExtensionRepository<GroupProjection> {
}
