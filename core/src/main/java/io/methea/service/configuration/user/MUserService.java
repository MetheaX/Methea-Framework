package io.methea.service.configuration.user;

import io.methea.domain.configuration.user.dto.UserBinder;
import io.methea.domain.configuration.user.entity.TUser;
import io.methea.domain.configuration.user.view.UserView;
import io.methea.repository.configuration.user.UserRepository;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
@Service
public class MUserService extends AbstractMetheaService<TUser, UserBinder, String, UserView> {
    public MUserService(UserRepository repository, HibernateExtensionRepository<UserView, String> extensionRepository) {
        super(UserView.class, repository, extensionRepository);
    }
}
