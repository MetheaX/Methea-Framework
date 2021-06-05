package io.github.metheax.service;

import io.github.metheax.domain.binder.UserBinder;
import io.github.metheax.domain.entity.TUser;
import io.github.metheax.domain.view.UserView;
import io.github.metheax.repository.UserRepository;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
@Service
public class UserService extends AbstractSimpleMetheaService<TUser, UserBinder, String, UserView> {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    public UserService(UserRepository repository, HibernateExtensionRepository<UserView, String> extensionRepository) {
        super(UserView.class, repository, extensionRepository);
        this.repository = repository;
    }

    public TUser resetUserPassword(String id, UserBinder binder) {
        try {
            Optional<TUser> optional = repository.findById(id);
            if (optional.isPresent()) {
                TUser user = optional.get();
                user.setForceUserResetPassword(binder.getForceUserResetPassword());
                user.setPassword(binder.getPassword());
                setModifiedAuditLog(user);
                repository.save(user);
                return user;
            }
        } catch (Exception ex) {
            log.error(">>>>> Reset user password error: ", ex);
        }
        return null;
    }
}
