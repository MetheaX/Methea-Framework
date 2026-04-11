package com.metheax.sena.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    // Minimal concrete subclass for testing the abstract hierarchy
    static class ConcreteEntity extends BaseEntity<ConcreteEntity> {
        public ConcreteEntity() { super(); }
        public ConcreteEntity(String status, String createdUser, LocalDateTime createdDate,
                              String updatedUser, LocalDateTime updatedDate) {
            super(status, createdUser, createdDate, updatedUser, updatedDate);
        }
        public ConcreteEntity(String updatedUser, LocalDateTime updatedDate) {
            super(updatedUser, updatedDate);
        }
        public ConcreteEntity(String status, String updatedUser, LocalDateTime updatedDate) {
            super(status, updatedUser, updatedDate);
        }
    }

    // ---- BaseEntity constructors ----

    @Test
    void defaultConstructor_allFieldsNull() {
        ConcreteEntity e = new ConcreteEntity();
        assertNull(e.getStatus());
        assertNull(e.getCreatedUser());
        assertNull(e.getCreatedDateTime());
        assertNull(e.getUpdatedUser());
        assertNull(e.getUpdatedDateTime());
    }

    @Test
    void createConstructor_setsAllFields() {
        LocalDateTime now = LocalDateTime.now();
        ConcreteEntity e = new ConcreteEntity("A", "admin", now, "admin", now);
        assertEquals("A", e.getStatus());
        assertEquals("admin", e.getCreatedUser());
        assertEquals(now, e.getCreatedDateTime());
        assertEquals("admin", e.getUpdatedUser());
        assertEquals(now, e.getUpdatedDateTime());
    }

    @Test
    void updateConstructor_setsUpdatedFields() {
        LocalDateTime now = LocalDateTime.now();
        ConcreteEntity e = new ConcreteEntity("editor", now);
        assertEquals("editor", e.getUpdatedUser());
        assertEquals(now, e.getUpdatedDateTime());
        assertNull(e.getStatus());
        assertNull(e.getCreatedUser());
    }

    @Test
    void activateConstructor_setsStatusAndUpdated() {
        LocalDateTime now = LocalDateTime.now();
        ConcreteEntity e = new ConcreteEntity("A", "system", now);
        assertEquals("A", e.getStatus());
        assertEquals("system", e.getUpdatedUser());
        assertEquals(now, e.getUpdatedDateTime());
        assertNull(e.getCreatedUser());
    }

    // ---- BaseEntity setters ----

    @Test
    void setters_updateAllFields() {
        LocalDateTime created = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime updated = LocalDateTime.of(2024, 6, 1, 12, 0);
        ConcreteEntity e = new ConcreteEntity();
        e.setStatus("I");
        e.setCreatedUser("creator");
        e.setCreatedDateTime(created);
        e.setUpdatedUser("updater");
        e.setUpdatedDateTime(updated);

        assertEquals("I", e.getStatus());
        assertEquals("creator", e.getCreatedUser());
        assertEquals(created, e.getCreatedDateTime());
        assertEquals("updater", e.getUpdatedUser());
        assertEquals(updated, e.getUpdatedDateTime());
    }

    // ---- AbstractMetheaEntity flags ----

    @Test
    void abstractEntity_defaultFlagsAreFalse() {
        ConcreteEntity e = new ConcreteEntity();
        assertFalse(e.isCreate());
        assertFalse(e.isUpdate());
        assertFalse(e.isActivate());
        assertFalse(e.isDeactivate());
    }

    @Test
    void abstractEntity_setCreate_true() {
        ConcreteEntity e = new ConcreteEntity();
        e.setCreate(true);
        assertTrue(e.isCreate());
        assertFalse(e.isUpdate());
    }

    @Test
    void abstractEntity_setUpdate_true() {
        ConcreteEntity e = new ConcreteEntity();
        e.setUpdate(true);
        assertTrue(e.isUpdate());
        assertFalse(e.isCreate());
    }

    @Test
    void abstractEntity_setActivate_true() {
        ConcreteEntity e = new ConcreteEntity();
        e.setActivate(true);
        assertTrue(e.isActivate());
        assertFalse(e.isDeactivate());
    }

    @Test
    void abstractEntity_setDeactivate_true() {
        ConcreteEntity e = new ConcreteEntity();
        e.setDeactivate(true);
        assertTrue(e.isDeactivate());
        assertFalse(e.isActivate());
    }
}
