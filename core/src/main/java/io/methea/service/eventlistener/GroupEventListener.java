package io.methea.service.eventlistener;

import io.methea.constant.MConstant;
import io.methea.domain.configuration.account.entity.TAccount;
import io.methea.domain.configuration.group.entity.TUserGroup;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.exception.AccountInactiveException;
import io.methea.repository.configuration.account.AccountRepository;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.service.configuration.user.MUserService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 31/05/2020
 */
@Service
public class GroupEventListener {

    private final MUserService userService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Inject
    public GroupEventListener(MUserService userService, UserRepository userRepository,
                              AccountRepository accountRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.activate")
    public void handleGroupActivate(TUserGroup entity) {
        Optional<TAccount> obj = accountRepository.findById(entity.getAccountId());
        if (obj.isPresent()) {
            TAccount account = obj.get();
            if (MConstant.INACTIVE_STATUS.equals(account.getStatus())) {
                throw new AccountInactiveException(String.format("Cannot activate group, account [%s] inactive!", account.getAccountName()));
            }
        }
        List<TUser> users = userRepository.findAllByGroupId(entity.getId());
        if (!CollectionUtils.isEmpty(users)) {
            for (TUser user : users) {
                userService.activateEntity(user.getId());
            }
        }
    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.deactivate")
    public void handleGroupDeactivate(TUserGroup entity) {
        List<TUser> users = userRepository.findAllByGroupId(entity.getId());
        if (!CollectionUtils.isEmpty(users)) {
            for (TUser user : users) {
                userService.deactivateEntity(user.getId());
            }
        }
    }
}
