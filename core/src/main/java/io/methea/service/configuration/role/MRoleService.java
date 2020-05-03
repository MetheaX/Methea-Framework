package io.methea.service.configuration.role;

import io.methea.domain.configuration.role.dto.RoleBinder;
import io.methea.domain.configuration.role.entity.TRole;
import io.methea.domain.configuration.role.view.RoleView;
import io.methea.repository.configuration.role.UserRoleRepository;
import io.methea.service.abs.AbstractMetheaService;

import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@Service
public class MRoleService extends AbstractMetheaService<TRole, RoleBinder, String, RoleView> {
    public MRoleService(UserRoleRepository repository) {
        super(RoleView.class, repository, repository);
    }
}
