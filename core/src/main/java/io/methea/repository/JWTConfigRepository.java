package io.methea.repository;

import io.methea.domain.entity.TJWTConfig;
import org.springframework.data.repository.CrudRepository;

public interface JWTConfigRepository extends CrudRepository<TJWTConfig, String> {
}
