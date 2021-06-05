package io.methea.service;

import io.methea.domain.binder.URIBinder;
import io.methea.domain.entity.TResource;
import io.methea.domain.view.URIView;
import io.methea.repository.ResourceRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@Service
public class URIService extends AbstractSimpleMetheaService<TResource, URIBinder, String, URIView> {
    public URIService(ResourceRepository repository) {
        super(URIView.class, repository, repository);
    }
}
