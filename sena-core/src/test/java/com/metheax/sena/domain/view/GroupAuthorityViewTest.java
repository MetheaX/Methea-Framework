package com.metheax.sena.domain.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupAuthorityViewTest {

    @Test
    void getAuthority_returnsGroupCode() {
        GroupAuthorityView view = new GroupAuthorityView("ADMIN_GRP");
        assertEquals("ADMIN_GRP", view.getAuthority());
    }

    @Test
    void getAuthority_nullGroupCode_returnsNull() {
        GroupAuthorityView view = new GroupAuthorityView(null);
        assertEquals(null, view.getAuthority());
    }
}
