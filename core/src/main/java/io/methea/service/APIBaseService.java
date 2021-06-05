package io.methea.service;

import io.methea.domain.binder.APIBaseBinder;
import io.methea.domain.entity.TAPIBase;
import io.methea.domain.view.APIBaseView;
import io.methea.repository.APIBaseRepository;
import io.methea.service.abs.AbstractSimpleMetheaService;
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
