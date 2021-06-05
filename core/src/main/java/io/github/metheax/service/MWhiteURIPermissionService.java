package io.github.metheax.service;

import io.github.metheax.domain.binder.WhiteURIPermissionBinder;
import io.github.metheax.domain.entity.TPublicPermission;
import io.github.metheax.domain.view.WhiteURIPermissionView;
import io.github.metheax.repository.WhiteURIPermissionRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
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
