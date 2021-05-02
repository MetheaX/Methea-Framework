package io.methea.service.eventlistener;

import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.group.entity.TGroup;
import io.methea.repository.configuration.group.UserGroupRepository;
import io.methea.service.configuration.group.MGroupService;
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

    private final UserGroupRepository groupRepository;
    private final MGroupService groupService;

    @Inject
    public AccountEventListener(UserGroupRepository groupRepository, MGroupService groupService) {
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
