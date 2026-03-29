package io.github.metheax.domain.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermissionViewTest {

    @Test
    void getGrantedPermission_returnsUriName() {
        PermissionView view = new PermissionView("/api/users/**");
        assertEquals("/api/users/**", view.getGrantedPermission());
    }

    @Test
    void getGrantedPermission_exactUri_returnsUri() {
        PermissionView view = new PermissionView("/api/health");
        assertEquals("/api/health", view.getGrantedPermission());
    }
}
