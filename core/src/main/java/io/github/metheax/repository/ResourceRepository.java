package io.github.metheax.repository;

import io.github.metheax.domain.entity.TResource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : Kuylim Tith
 * Date : 15/09/2019
 */
public interface ResourceRepository extends JpaRepository<TResource, String> {
}
