package io.methea.service;

import io.methea.domain.binder.GroupBinder;
import io.methea.domain.entity.TGroup;
import io.methea.domain.view.GroupView;
import io.methea.repository.GroupRepository;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 16/04/2020
 */
@Service
public class GroupService extends AbstractSimpleMetheaService<TGroup, GroupBinder, String, GroupView> {

    @Inject
    public GroupService(GroupRepository repository,
                        HibernateExtensionRepository<GroupView, String> extensionRepository) {
        super(GroupView.class, repository, extensionRepository);
    }
}
