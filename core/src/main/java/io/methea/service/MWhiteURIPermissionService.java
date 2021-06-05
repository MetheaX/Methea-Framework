package io.methea.service;

import io.methea.domain.binder.WhiteURIPermissionBinder;
import io.methea.domain.entity.TPublicPermission;
import io.methea.domain.view.WhiteURIPermissionView;
import io.methea.repository.WhiteURIPermissionRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 17/10/2020
 */
@Service
public class MWhiteURIPermissionService extends AbstractSimpleMetheaService<TPublicPermission, WhiteURIPermissionBinder,
        String, WhiteURIPermissionView> {
    public MWhiteURIPermissionService(WhiteURIPermissionRepository repository) {
        super(WhiteURIPermissionView.class, repository, repository);
    }
}
