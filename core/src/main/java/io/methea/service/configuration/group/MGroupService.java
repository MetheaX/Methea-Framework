package io.methea.service.configuration.group;

import io.methea.domain.configuration.group.dto.UserGroupBinder;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.group.view.GroupView;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.service.abs.AbstractEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@Service
public class MGroupService extends AbstractEntityService<TUserGroup, UserGroupBinder, String, GroupView> {

    private static Logger log = LoggerFactory.getLogger(MGroupService.class);

    @Inject
    public MGroupService(UserGroupRepository repository,
                         HibernateExtensionRepository<GroupView, String> extensionRepository) {
        super(GroupView.class, repository, extensionRepository);
    }
}
