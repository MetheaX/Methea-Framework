package io.github.metheax.repository;

import io.github.metheax.domain.entity.TAPIBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APIBaseRepository extends JpaRepository<TAPIBase, String> {
    List<TAPIBase> findAllByIdIn(List<String> id);
}
