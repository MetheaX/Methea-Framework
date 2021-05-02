package io.methea.service.configuration.uri;

import io.methea.domain.configuration.resource.dto.URIBinder;
import io.methea.domain.configuration.resource.entity.TResource;
import io.methea.domain.configuration.resource.view.URIView;
import io.methea.repository.configuration.uri.URIRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@Service
public class URIService extends AbstractSimpleMetheaService<TResource, URIBinder, String, URIView> {
    public URIService(URIRepository repository) {
        super(URIView.class, repository, repository);
    }
}
