package io.methea.service;

import io.methea.domain.binder.RoleBinder;
import io.methea.domain.entity.TRole;
import io.methea.domain.view.RoleView;
import io.methea.repository.RoleRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;

import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 02/05/2020
 */
@Service
public class RoleService extends AbstractSimpleMetheaService<TRole, RoleBinder, String, RoleView> {
    public RoleService(RoleRepository repository) {
        super(RoleView.class, repository, repository);
    }
}
