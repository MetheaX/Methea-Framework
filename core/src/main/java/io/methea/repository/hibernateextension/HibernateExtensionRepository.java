package io.methea.repository.hibernateextension;

import io.methea.repository.hibernateextension.domain.HibernatePage;

import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
public interface HibernateExtensionRepository<V, ID> {
    List<Object[]> getByNativeQuery(String nativeSQL);

    List<V> getByQuery(String hql, Map<String, Object> parameters, Class<V> view, int limit, int offset);

    List<V> getByQuery(Map<String, Object> parameters, Class<V> view);

    V getEntityById(Class<V> view, ID id);

    HibernatePage<V> getByQuery(Map<String, Object> parameters, Class<V> view, int limit, int offset);

    long count(String hql, Map<String, Object> parameters);
}
