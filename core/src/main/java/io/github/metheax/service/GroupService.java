package io.github.metheax.service;

import io.github.metheax.domain.binder.GroupBinder;
import io.github.metheax.domain.entity.TGroup;
import io.github.metheax.domain.view.GroupView;
import io.github.metheax.repository.GroupRepository;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
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
