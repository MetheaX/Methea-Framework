package io.github.metheax.repository;

import io.github.metheax.domain.entity.TJWTConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JWTConfigRepository extends JpaRepository<TJWTConfig, String> {
}
