package com.metheax.sena.repository.hibernateextension.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernatePageTest {

    @Test
    void constructorWithContentOnly_totalCountIsZero() {
        List<String> content = Arrays.asList("a", "b", "c");
        HibernatePage<String> page = new HibernatePage<>(content);
        assertEquals(content, page.getContent());
        assertEquals(0L, page.getTotalCount());
    }

    @Test
    void constructorWithContentAndCount_setsBothFields() {
        List<String> content = Arrays.asList("x", "y");
        HibernatePage<String> page = new HibernatePage<>(content, 42L);
        assertEquals(content, page.getContent());
        assertEquals(42L, page.getTotalCount());
    }

    @Test
    void constructorWithEmptyList_returnsEmptyContent() {
        HibernatePage<String> page = new HibernatePage<>(Collections.emptyList(), 0L);
        assertTrue(page.getContent().isEmpty());
        assertEquals(0L, page.getTotalCount());
    }
}
