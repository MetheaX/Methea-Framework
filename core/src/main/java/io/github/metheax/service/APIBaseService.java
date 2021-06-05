package io.github.metheax.service;

import io.github.metheax.repository.APIBaseRepository;
import io.github.metheax.domain.binder.APIBaseBinder;
import io.github.metheax.domain.entity.TAPIBase;
import io.github.metheax.domain.view.APIBaseView;
import io.github.metheax.service.abs.AbstractSimpleMetheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@Service
public class APIBaseService extends AbstractSimpleMetheaService<TAPIBase, APIBaseBinder, String, APIBaseView> {
    private static final Logger log = LoggerFactory.getLogger(APIBaseService.class);

    @Inject
    public APIBaseService(APIBaseRepository repository) {
        super(APIBaseView.class, repository, repository);
    }
}
