package io.methea.repository.configuration.display;

import io.methea.domain.configuration.display.entity.TDataTableView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : DKSilverX
 * Date : 18/01/2020
 */
public interface DisplayRepository extends CrudRepository<TDataTableView, String> {
    List<TDataTableView> findAllByViewNameAndStatusOrderBySequence(String viewName, String status);
    List<TDataTableView> findAllByViewNameAndStatusAndAllowFilterOrderBySequence(String viewName, String status, String allowFilter);
}
