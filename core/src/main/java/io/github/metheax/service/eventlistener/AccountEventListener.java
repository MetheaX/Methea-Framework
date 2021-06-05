package io.github.metheax.service.eventlistener;

import io.github.metheax.domain.entity.TAccount;
import io.github.metheax.domain.entity.TGroup;
import io.github.metheax.repository.GroupRepository;
import io.github.metheax.service.GroupService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class AccountEventListener {

    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @Inject
    public AccountEventListener(GroupRepository groupRepository, GroupService groupService) {
        this.groupRepository = groupRepository;
        this.groupService = groupService;
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.activate")
    public void handleAccountActivate(TAccount entity) {
        List<TGroup> groups = groupRepository.findAllByAccountId(entity.getId());
        if (!CollectionUtils.isEmpty(groups)) {
            for (TGroup group : groups) {
                groupService.activateEntity(group.getId());
            }
        }
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.deactivate")
    public void handleAccountDeactivate(TAccount entity) {
        List<TGroup> groups = groupRepository.findAllByAccountId(entity.getId());
        if (!CollectionUtils.isEmpty(groups)) {
            for (TGroup group : groups) {
                groupService.deactivateEntity(group.getId());
            }
        }
    }
}
