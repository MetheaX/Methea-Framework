package io.github.metheax.repository.hibernateextension.impl;

import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;
import io.github.metheax.repository.hibernateextension.domain.HibernatePage;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : Kuylim Tith
 * Date : 14/04/2020
 */
@Repository
@SuppressWarnings("unchecked")
public class HibernateExtensionRepositoryImpl<V, ID> implements HibernateExtensionRepository<V, ID> {

    private static final Logger log = LoggerFactory.getLogger(HibernateExtensionRepositoryImpl.class);

    private SessionFactory sessionFactory;
    private static final String SPACE = " ";

    @Autowired
    public HibernateExtensionRepositoryImpl(final EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
    public List<V> getByQuery(String hql, Map<String, Object> parameters, Class<V> view, int limit, int offset) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql, view);
            setCriteria(query, parameters);

            if (limit > 0) {
                query.setMaxResults(limit);
            }
            if (offset > 0) {
                query.setFirstResult(offset);
            }
            return (List<V>) query.list();
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] getByQuery error: ", ex);
        }
    }

    @Override
    public List<V> getByQuery(Map<String, Object> parameters, Class<V> view) {
        return queryWithPage(parameters, view, 0, 0, false).getContent();
    }

    @Override
    public V getEntityById(Class<V> view, ID id) {
        return queryByID(view, id);
    }

    @Override
    public HibernatePage<V> getByQuery(Map<String, Object> parameters, Class<V> view, int limit, int offset) {
        return queryWithPage(parameters, view, limit, offset, true);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public long count(String hql, Map<String, Object> parameters) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            setCriteria(query, parameters);
            Number number = (Number) query.uniqueResult();
            log.info("========> [HibernateExtensionRepositoryImpl] count: {}", number);
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

    private HibernatePage<V> queryWithPage(Map<String, Object> parameters, Class<V> view, int limit, int offset, boolean isCount) {
        String hql = StringUtils.EMPTY;
        String selectClause = "SELECT new ".concat(view.getName()).concat("(");
        String countHQL = "SELECT DISTINCT COUNT (o.id) ".concat(view.getAnnotation(SelectFrom.class).fromClause());
        String whereClause = " WHERE 1=1 ";
        try {
            Field[] fields = view.getDeclaredFields();
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
            hql = hql.concat(selectClause).concat(view.getAnnotation(SelectFrom.class).fromClause()).concat(SPACE).concat(whereClause)
                    .concat(SPACE).concat(view.getAnnotation(SelectFrom.class).orderBy());
            if (isCount) {
                countHQL = countHQL.concat(whereClause);
                return new HibernatePage<V>(getByQuery(hql, parameters, view, limit, offset), count(countHQL, parameters));
            }
            return new HibernatePage<V>(getByQuery(hql, parameters, view, limit, offset));
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] Failed to generate hibernate query language: ", ex);
        }
    }

    private V queryByID(Class<V> view, ID id) {
        String hql = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        String selectClause = "SELECT DISTINCT new ".concat(view.getName()).concat("(");
        String whereClause = " WHERE 1=1 ";
        try {
            Field[] fields = view.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (!field.getAnnotation(Column.class).isLastColumn()) {
                        selectClause = selectClause.concat(field.getAnnotation(Column.class).name()).concat(",");
                    } else {
                        selectClause = selectClause.concat(field.getAnnotation(Column.class).name());
                    }
                }
            }
            selectClause = selectClause.concat(")").concat(SPACE);
            hql = hql.concat(selectClause).concat(view.getAnnotation(SelectFrom.class).fromClause()).concat(SPACE)
                    .concat(whereClause).concat(" AND ").concat(view.getAnnotation(SelectFrom.class).getById())
                    .concat(StringUtils.isEmpty(view.getAnnotation(SelectFrom.class).join()) ? StringUtils.EMPTY : " AND ")
                    .concat(view.getAnnotation(SelectFrom.class).join());
            List<V> page = getByQuery(hql, parameters, view, 0, 0);
            if (!CollectionUtils.isEmpty(page)) {
                return page.get(0);
            }
            return null;
        } catch (Exception ex) {
            throw new RuntimeException("[HibernateExtensionRepositoryImpl] Failed to generate hibernate query language: ", ex);
        }
    }
}
