package io.methea.service.configuration.uri;

import io.methea.domain.configuration.uri.dto.RoleURIBinder;
import io.methea.domain.configuration.uri.entity.TRoleURI;
import io.methea.domain.configuration.uri.view.RoleURIView;
import io.methea.repository.configuration.uri.RoleUIRRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 16/05/2020
 */
@Service
public class RoleURIService extends AbstractMetheaService<TRoleURI, RoleURIBinder, String, RoleURIView> {
    public RoleURIService(RoleUIRRepository repository) {
        super(RoleURIView.class, repository, repository);
    }
}
