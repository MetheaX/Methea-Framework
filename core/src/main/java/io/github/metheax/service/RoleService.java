package io.github.metheax.service;

import io.github.metheax.domain.binder.RoleBinder;
import io.github.metheax.domain.entity.TRole;
import io.github.metheax.domain.view.RoleView;
import io.github.metheax.repository.RoleRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;

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
