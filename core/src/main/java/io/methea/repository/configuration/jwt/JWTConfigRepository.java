package io.methea.repository.configuration.jwt;

import io.methea.domain.configuration.jwt.entity.TJWTConfig;
import org.springframework.data.repository.CrudRepository;

public interface JWTConfigRepository extends CrudRepository<TJWTConfig, String> {
}
