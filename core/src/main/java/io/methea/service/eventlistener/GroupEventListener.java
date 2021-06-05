package io.methea.service.eventlistener;

import io.methea.domain.entity.TGroup;
import io.methea.domain.entity.TUser;
import io.methea.repository.AccountRepository;
import io.methea.repository.UserRepository;
import io.methea.service.UserService;
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
public class GroupEventListener {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Inject
    public GroupEventListener(UserService userService, UserRepository userRepository,
                              AccountRepository accountRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

//    @Async
//    @Transactional
//    @EventListener(condition = "#entity.activate")
//    public void handleGroupActivate(TGroup entity) {
//        Optional<TAccount> obj = accountRepository.findById(entity.getAccountId());
//        if (obj.isPresent()) {
//            TAccount account = obj.get();
//            if (MConstant.INACTIVE_STATUS.equals(account.getStatus())) {
//                throw new AccountInactiveException(String.format("Cannot activate group, account [%s] inactive!", account.getAccountName()));
//            }
//        }
//        List<TUser> users = userRepository.findAllByGroupId(entity.getId());
//        if (!CollectionUtils.isEmpty(users)) {
//            for (TUser user : users) {
//                userService.activateEntity(user.getId());
//            }
//        }
//    }

    @Async
    @Transactional
    @EventListener(condition = "#entity.deactivate")
    public void handleGroupDeactivate(TGroup entity) {
        List<TUser> users = userRepository.findAllByGroupId(entity.getId());
        if (!CollectionUtils.isEmpty(users)) {
            for (TUser user : users) {
                userService.deactivateEntity(user.getId());
            }
        }
    }
}
