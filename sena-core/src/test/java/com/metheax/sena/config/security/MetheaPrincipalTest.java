package com.metheax.sena.config.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetheaPrincipalTest {

    @Test
    void defaultConstructor_allFieldsAreNull() {
        MetheaPrincipal p = new MetheaPrincipal();
        assertNull(p.getUsername());
        assertNull(p.getGroupCode());
        assertNull(p.getPhone());
        assertNull(p.getEmail());
        assertNull(p.getForceUserResetPassword());
    }

    @Test
    void parameterizedConstructor_setsFields() {
        MetheaPrincipal p = new MetheaPrincipal("john", "555-1234", "john@example.com", "N");
        assertEquals("john", p.getUsername());
        assertEquals("555-1234", p.getPhone());
        assertEquals("john@example.com", p.getEmail());
        assertEquals("N", p.getForceUserResetPassword());
        // groupCode is not set via this constructor
        assertNull(p.getGroupCode());
    }

    @Test
    void setters_updateAllFields() {
        MetheaPrincipal p = new MetheaPrincipal();
        p.setUsername("alice");
        p.setGroupCode("HR_GRP");
        p.setPhone("099-123456");
        p.setEmail("alice@corp.com");
        p.setForceUserResetPassword("Y");

        assertEquals("alice", p.getUsername());
        assertEquals("HR_GRP", p.getGroupCode());
        assertEquals("099-123456", p.getPhone());
        assertEquals("alice@corp.com", p.getEmail());
        assertEquals("Y", p.getForceUserResetPassword());
    }

    @Test
    void setGroupCode_updatesGroupCode() {
        MetheaPrincipal p = new MetheaPrincipal("user", null, null, null);
        p.setGroupCode("ADMIN_GRP");
        assertEquals("ADMIN_GRP", p.getGroupCode());
    }

    @Test
    void setForceUserResetPassword_toNo_persistsValue() {
        MetheaPrincipal p = new MetheaPrincipal();
        p.setForceUserResetPassword("N");
        assertEquals("N", p.getForceUserResetPassword());
    }
}
