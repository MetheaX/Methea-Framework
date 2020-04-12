package io.methea.repository.customnative.impl;

import io.methea.repository.customnative.CustomNativeRepository;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Author : DKSilverX
 * Date : 03/03/2020
 */
@Repository
public class CustomNativeRepositoryImpl implements CustomNativeRepository {

    private final EntityManager entityManager;

    @Inject
    public CustomNativeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Object[]> getByNativeQuery(String nativeSQL) {
        return entityManager.createNativeQuery(nativeSQL)
                .getResultList();
    }
}
