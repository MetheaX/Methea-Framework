package io.methea.service.webservice.apibase;

import io.methea.domain.webservice.baseapi.dto.APIBaseBinder;
import io.methea.domain.webservice.baseapi.entity.APIBase;
import io.methea.domain.webservice.baseapi.view.APIBaseView;
import io.methea.repository.webservice.baseapi.APIBaseRepository;
import io.methea.service.abs.AbstractMetheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 06/06/2020
 */
@Service
public class MAPIBaseService extends AbstractMetheaService<APIBase, APIBaseBinder, String, APIBaseView> {
    private static Logger log = LoggerFactory.getLogger(MAPIBaseService.class);

    @Inject
    public MAPIBaseService(APIBaseRepository repository) {
        super(APIBaseView.class, repository, repository);
    }
}
