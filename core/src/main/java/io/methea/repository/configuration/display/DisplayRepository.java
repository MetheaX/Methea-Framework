package io.methea.repository.configuration.display;

import io.methea.domain.configuration.display.entity.TDataTableView;
import io.methea.domain.configuration.display.view.DataTableView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
public interface DisplayRepository extends CrudRepository<TDataTableView, String>, HibernateExtensionRepository<DataTableView, String> {
    List<TDataTableView> findAllByViewNameAndStatusOrderBySequence(String viewName, String status);
    List<TDataTableView> findAllByViewNameAndStatusAndAllowFilterOrderBySequence(String viewName, String status, String allowFilter);
}
