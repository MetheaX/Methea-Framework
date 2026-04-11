package com.metheax.sena.repository;

import com.metheax.sena.domain.entity.JWTConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JWTConfigRepository extends JpaRepository<JWTConfig, String> {
}
