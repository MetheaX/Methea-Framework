package io.github.metheax.repository;

import io.github.metheax.domain.entity.TAPIBase;
import io.github.metheax.domain.view.APIBaseView;
import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface APIBaseRepository extends CrudRepository<TAPIBase, String>,
        HibernateExtensionRepository<APIBaseView, String> {
    List<TAPIBase> findAllByIdIn(List<String> id);
}
