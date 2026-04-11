package com.metheax.sena.repository;

import com.metheax.sena.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 15/09/2019
 */
public interface ResourceRepository extends JpaRepository<Resource, UUID> {
}
