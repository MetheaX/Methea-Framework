package io.methea.repository;

import io.methea.domain.entity.TAPIBase;
import io.methea.domain.view.APIBaseView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface APIBaseRepository extends CrudRepository<TAPIBase, String>,
        HibernateExtensionRepository<APIBaseView, String> {
    List<TAPIBase> findAllByIdIn(List<String> id);
}
