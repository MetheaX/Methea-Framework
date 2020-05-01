package io.methea.service.configuration.user;

import io.methea.domain.configuration.user.dto.UserBinder;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.domain.configuration.user.view.UserView;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
@Service
public class MUserService extends AbstractMetheaService<TUser, UserBinder, String, UserView> {

    private static Logger log = LoggerFactory.getLogger(MUserService.class);
    private final UserRepository repository;

    public MUserService(UserRepository repository, HibernateExtensionRepository<UserView, String> extensionRepository) {
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
