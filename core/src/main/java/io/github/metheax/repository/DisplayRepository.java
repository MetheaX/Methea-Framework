package io.github.metheax.repository;

import io.github.metheax.domain.entity.TDataTableView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author : Kuylim Tith
 * Date : 18/01/2020
 */
public interface DisplayRepository extends JpaRepository<TDataTableView, String> {
    List<TDataTableView> findAllByViewNameAndStatusOrderBySequence(String viewName, String status);
    List<TDataTableView> findAllByViewNameAndStatusAndAllowFilterOrderBySequence(String viewName, String status, String allowFilter);
}
