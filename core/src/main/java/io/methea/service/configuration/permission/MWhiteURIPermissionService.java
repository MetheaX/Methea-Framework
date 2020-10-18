package io.methea.service.configuration.permission;

import io.methea.domain.configuration.permission.dto.WhiteURIPermissionBinder;
import io.methea.domain.configuration.permission.entity.TWhiteURIPermission;
import io.methea.domain.configuration.permission.view.WhiteURIPermissionView;
import io.methea.repository.configuration.permission.WhiteURIPermissionRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 17/10/2020
 */
@Service
public class MWhiteURIPermissionService extends AbstractSimpleMetheaService<TWhiteURIPermission, WhiteURIPermissionBinder,
        String, WhiteURIPermissionView> {
    public MWhiteURIPermissionService(WhiteURIPermissionRepository repository) {
        super(WhiteURIPermissionView.class, repository, repository);
    }
}
