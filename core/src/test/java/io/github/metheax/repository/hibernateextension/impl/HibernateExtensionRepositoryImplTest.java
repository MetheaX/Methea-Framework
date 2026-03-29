package io.github.metheax.repository.hibernateextension.impl;

import io.github.metheax.repository.hibernateextension.annotation.Column;
import io.github.metheax.repository.hibernateextension.annotation.SelectFrom;
import io.github.metheax.repository.hibernateextension.domain.HibernatePage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SuppressWarnings({"unchecked", "rawtypes"})
@ExtendWith(MockitoExtension.class)
class HibernateExtensionRepositoryImplTest {

    @Mock EntityManagerFactory emf;
    @Mock EntityManager em;
    @Mock Session constructorSession;
    @Mock SessionFactory sessionFactory;
    @Mock Session openedSession;
    @Mock Query query;

    private HibernateExtensionRepositoryImpl<TestView, UUID> repo;

    // ---- Minimal view class with @SelectFrom / @Column ----

    @SelectFrom(
            fromClause = "FROM TestEntity o",
            orderBy = "ORDER BY o.id",
            getById = "o.id = :id",
            join = ""
    )
    static class TestView {
        @Column(name = "o.name", key = "name", where = "AND o.name = :name")
        private String name;

        @Column(name = "o.id", key = "id", where = "AND o.id = :id", isLastColumn = true)
        private String id;

        public TestView(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }

    @BeforeEach
    void setUp() {
        when(emf.createEntityManager()).thenReturn(em);
        when(em.unwrap(Session.class)).thenReturn(constructorSession);
        when(constructorSession.getSessionFactory()).thenReturn(sessionFactory);

        repo = new HibernateExtensionRepositoryImpl<>(emf);
    }

    // ---- getByNativeQuery ----

    @Test
    void getByNativeQuery_returnsResultList() {
        Object[] row = {"a", "1"};
        List<Object[]> expected = Collections.singletonList(row);
        NativeQuery nativeQuery = mock(NativeQuery.class);
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createNativeQuery(anyString())).thenReturn(nativeQuery);
        when(nativeQuery.getResultList()).thenReturn(expected);

        List<Object[]> result = repo.getByNativeQuery("SELECT * FROM test");
        assertEquals(expected, result);
    }

    @Test
    void getByNativeQuery_sessionThrows_wrapsException() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createNativeQuery(anyString())).thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> repo.getByNativeQuery("SELECT 1"));
    }

    // ---- getByQuery (HQL, params, class, limit, offset) ----

    @Test
    void getByQuery_hql_withLimitAndOffset_appliesBoth() {
        List<TestView> expected = List.of(new TestView("Alice", "1"));
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(expected);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Alice");

        List<TestView> result = repo.getByQuery("FROM TestEntity o WHERE o.name=:name", params, TestView.class, 10, 5);

        verify(query).setMaxResults(10);
        verify(query).setFirstResult(5);
        assertEquals(expected, result);
    }

    @Test
    void getByQuery_hql_noLimitNoOffset_doesNotSetPaging() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(Collections.emptyList());

        repo.getByQuery("FROM TestEntity", Collections.emptyMap(), TestView.class, 0, 0);

        verify(query, never()).setMaxResults(anyInt());
        verify(query, never()).setFirstResult(anyInt());
    }

    @Test
    void getByQuery_hql_withCollectionParam_callsSetParameterList() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(Collections.emptyList());

        Map<String, Object> params = new HashMap<>();
        params.put("ids", List.of("1", "2", "3"));

        repo.getByQuery("FROM TestEntity o WHERE o.id IN :ids", params, TestView.class, 0, 0);

        verify(query).setParameterList(eq("ids"), any(Collection.class));
    }

    @Test
    void getByQuery_hql_sessionThrows_wrapsException() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenThrow(new RuntimeException("HQL error"));

        assertThrows(RuntimeException.class,
                () -> repo.getByQuery("INVALID HQL", Collections.emptyMap(), TestView.class, 0, 0));
    }

    // ---- getByQuery (Map, Class) — delegates to queryWithPage(isCount=false) ----

    @Test
    void getByQuery_mapAndClass_returnsContent() {
        List<TestView> expected = List.of(new TestView("Bob", "2"));
        // queryWithPage calls getByQuery(hql, params, class, 0, 0) → openedSession
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(expected);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Bob");

        List<TestView> result = repo.getByQuery(params, TestView.class);
        assertEquals(expected, result);
    }

    @Test
    void getByQuery_mapAndClass_emptyParams_returnsContent() {
        List<TestView> expected = Collections.emptyList();
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<TestView> result = repo.getByQuery(Collections.emptyMap(), TestView.class);
        assertEquals(expected, result);
    }

    // ---- getByQuery (Map, Class, limit, offset) — delegates to queryWithPage(isCount=true) ----

    @Test
    void getByQuery_paged_returnsHibernatePageWithCount() {
        List<TestView> data = Collections.singletonList(new TestView("Carol", "3"));
        Query countQuery = mock(Query.class);

        when(sessionFactory.openSession()).thenReturn(openedSession);
        // data query uses the 2-arg createQuery(String, Class)
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(data);
        // count query uses the 1-arg createQuery(String)
        when(openedSession.createQuery(anyString())).thenReturn(countQuery);
        when(countQuery.uniqueResult()).thenReturn(1L);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "Carol");

        HibernatePage<TestView> page = repo.getByQuery(params, TestView.class, 10, 0);
        assertNotNull(page);
        assertEquals(data, page.getContent());
    }

    // ---- getEntityById ----

    @Test
    void getEntityById_found_returnsFirstResult() {
        TestView expected = new TestView("Dave", "4");
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(expected));

        TestView result = repo.getEntityById(TestView.class, UUID.randomUUID());
        assertEquals(expected, result);
    }

    @Test
    void getEntityById_notFound_returnsNull() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString(), eq(TestView.class))).thenReturn(query);
        when(query.list()).thenReturn(Collections.emptyList());

        TestView result = repo.getEntityById(TestView.class, UUID.randomUUID());
        assertNull(result);
    }

    // ---- count ----

    @Test
    void count_returnsLongValue() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        Query countQuery = mock(Query.class);
        when(openedSession.createQuery(anyString())).thenReturn(countQuery);
        when(countQuery.uniqueResult()).thenReturn(7L);

        long result = repo.count("SELECT COUNT(o.id) FROM TestEntity o", Collections.emptyMap());
        assertEquals(7L, result);
    }

    @Test
    void count_withParams_setsParameters() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        Query countQuery = mock(Query.class);
        when(openedSession.createQuery(anyString())).thenReturn(countQuery);
        when(countQuery.uniqueResult()).thenReturn(3L);

        Map<String, Object> params = new HashMap<>();
        params.put("status", "A");

        repo.count("SELECT COUNT(o.id) FROM TestEntity o WHERE o.status=:status", params);

        verify(countQuery).setParameter("status", "A");
    }

    @Test
    void count_sessionThrows_wrapsException() {
        when(sessionFactory.openSession()).thenReturn(openedSession);
        when(openedSession.createQuery(anyString())).thenThrow(new RuntimeException("count error"));

        assertThrows(RuntimeException.class,
                () -> repo.count("SELECT COUNT(*) FROM x", Collections.emptyMap()));
    }
}
