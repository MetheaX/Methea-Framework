package io.methea.service.configuration.uri;

import io.methea.domain.configuration.uri.dto.RoleURIBinder;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.uri.view.RoleURIView;
import io.methea.repository.configuration.uri.RoleURIRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@Service
public class RoleURIService extends AbstractSimpleMetheaService<TRoleURI, RoleURIBinder, String, RoleURIView> {
    public RoleURIService(RoleURIRepository repository) {
        super(RoleURIView.class, repository, repository);
    }
}
