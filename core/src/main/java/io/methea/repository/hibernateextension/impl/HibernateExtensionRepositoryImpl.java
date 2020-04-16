package io.methea.repository.hibernateextension.impl;

import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.repository.hibernateextension.annotation.Column;
import io.methea.repository.hibernateextension.annotation.SelectFrom;
import io.methea.repository.hibernateextension.domain.HibernatePage;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
@Repository
@SuppressWarnings("unchecked")
public class HibernateExtensionRepositoryImpl<T> implements HibernateExtensionRepository<T> {

    private SessionFactory sessionFactory;
    private EntityManager entityManager;
    private static final String SPACE = " ";

    @Autowired
    public HibernateExtensionRepositoryImpl(final EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.createEntityManager();
        sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
    }

    @Override
    public List<Object[]> getByNativeQuery(String nativeSQL) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery(nativeSQL)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] getByNativeQuery error: ", ex);
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List<T> getByQuery(String hql, Map<String, Object> parameters, Class<T> payload, int limit, int offset) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql, payload);
            setCriteria(query, parameters);

            if (limit > 0) {
                query.setMaxResults(limit);
            }
            if (offset > 0) {
                query.setFirstResult(offset);
            }
            return (List<T>) query.list();
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] getByQuery error: ", ex);
        }
    }

    @Override
    public List<T> getByQuery(Map<String, Object> parameters, Class<T> payload) {
        return queryWithPage(parameters, payload, 0, 0, false).getContent();
    }

    @Override
    public HibernatePage<T> getByQuery(Map<String, Object> parameters, Class<T> payload, int limit, int offset) {
        return queryWithPage(parameters, payload, limit, offset, true);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public long count(String hql, Map<String, Object> parameters) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            setCriteria(query, parameters);
            Number number = (Number) query.uniqueResult();
            return number.longValue();
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] count error: ", ex);
        }
    }

    @SuppressWarnings("rawtypes")
    private void setCriteria(Query query, Map<String, Object> parameters) {
        if (!CollectionUtils.isEmpty(parameters)) {
            for (String param : parameters.keySet()) {
                Object value = parameters.get(param);
                if (value instanceof Collection) {
                    query.setParameterList(param, (Collection) value);
                } else {
                    query.setParameter(param, value);
                }
            }
        }
    }

    private HibernatePage<T> queryWithPage(Map<String, Object> parameters, Class<T> payload, int limit, int offset, boolean isCount) {
        String hql = StringUtils.EMPTY;
        String selectClause = "SELECT new ".concat(payload.getName()).concat("(");
        String countHQL = "SELECT DISTINCT COUNT (o.id) ".concat(payload.getAnnotation(SelectFrom.class).fromClause());
        String whereClause = " WHERE 1=1 ";
        try {
            Field[] fields = payload.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (!field.getAnnotation(Column.class).isLastColumn()) {
                        selectClause = selectClause.concat(field.getAnnotation(Column.class).name()).concat(",");
                    } else {
                        selectClause = selectClause.concat(field.getAnnotation(Column.class).name());
                    }
                    if (parameters.containsKey(field.getAnnotation(Column.class).key())) {
                        whereClause = whereClause.concat(SPACE).concat(field.getAnnotation(Column.class).where()).concat(SPACE);
                    }
                }
            }
            selectClause = selectClause.concat(")").concat(SPACE);
            hql = hql.concat(selectClause).concat(payload.getAnnotation(SelectFrom.class).fromClause()).concat(SPACE).concat(whereClause)
                    .concat(SPACE).concat(payload.getAnnotation(SelectFrom.class).orderBy());
            if (isCount) {
                countHQL = countHQL.concat(whereClause);
                return new HibernatePage<T>(getByQuery(hql, parameters, payload, limit, offset), count(countHQL, parameters));
            }
            return new HibernatePage<T>(getByQuery(hql, parameters, payload, limit, offset));
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] Failed to generate hibernate query language: ", ex);
        }
    }
}