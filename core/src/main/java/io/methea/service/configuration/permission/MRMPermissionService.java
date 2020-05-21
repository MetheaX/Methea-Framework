package io.methea.service.configuration.permission;

import io.methea.domain.configuration.permission.dto.RMPermissionBinder;
import io.methea.domain.configuration.permission.entity.TRMUserPermission;
import io.methea.domain.configuration.permission.view.RMPermissionView;
import io.methea.repository.configuration.permission.RMUserGrantedPermissionRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 19/05/2020
 */
@Service
public class MRMPermissionService extends AbstractMetheaService<TRMUserPermission, RMPermissionBinder, String,
        RMPermissionView> {
    public MRMPermissionService(RMUserGrantedPermissionRepository repository) {
        super(RMPermissionView.class, repository, repository);
    }
}
