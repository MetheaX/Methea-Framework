package io.methea.service.configuration.uri;

import io.methea.domain.configuration.uri.dto.URIBinder;
import io.methea.domain.configuration.uri.entity.TMstURI;
import io.methea.domain.configuration.uri.view.URIView;
import io.methea.repository.configuration.uri.URIRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.springframework.stereotype.Service;

/**
 * Author : DKSilverX
 * Date : 06/05/2020
 */
@Service
public class URIService extends AbstractMetheaService<TMstURI, URIBinder, String, URIView> {
    public URIService(URIRepository repository) {
        super(URIView.class, repository, repository);
    }
}
