package io.methea.repository.hibernateextension;

import io.methea.repository.hibernateextension.domain.HibernatePage;

import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
public interface HibernateExtensionRepository<T> {
    List<Object[]> getByNativeQuery(String nativeSQL);

    List<T> getByQuery(String hql, Map<String, Object> parameters, Class<T> payload, int limit, int offset);

    List<T> getByQuery(Map<String, Object> parameters, Class<T> payload);

    HibernatePage<T> getByQuery(Map<String, Object> parameters, Class<T> payload, int limit, int offset);

    long count(String hql, Map<String, Object> parameters);
}
