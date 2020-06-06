package io.methea.repository.webservice.baseapi;

import io.methea.domain.webservice.baseapi.entity.APIBase;
import io.methea.domain.webservice.baseapi.view.APIBaseView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

public interface APIBaseRepository extends CrudRepository<APIBase, String>,
        HibernateExtensionRepository<APIBaseView, String> {
}
