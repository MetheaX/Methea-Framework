package io.github.metheax.service;

import io.github.metheax.domain.entity.TResource;
import io.github.metheax.domain.binder.URIBinder;
import io.github.metheax.domain.view.URIView;
import io.github.metheax.repository.ResourceRepository;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
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
