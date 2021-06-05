package io.github.metheax.repository;

import io.github.metheax.domain.entity.TJWTConfig;
import org.springframework.data.repository.CrudRepository;

public interface JWTConfigRepository extends CrudRepository<TJWTConfig, String> {
}
