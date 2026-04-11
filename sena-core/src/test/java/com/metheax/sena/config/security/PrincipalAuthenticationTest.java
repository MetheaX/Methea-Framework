package com.metheax.sena.config.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrincipalAuthenticationTest {

    @Test
    void constructor_usernamePasswordAuthorities_setsEmptyPermissionsAndNullPrincipal() {
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "user", "pass", Collections.emptyList());

        assertEquals("user", auth.getUsername());
        assertNotNull(auth.getGrantedPermissions());
        assertTrue(auth.getGrantedPermissions().isEmpty());
        assertNull(auth.getMetheaPrincipal());
    }

    @Test
    void constructor_withGrantedPermissions_setsPermissions() {
        GrantedPermission perm = () -> "/api/resource/**";
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "user", "pass", Collections.emptyList(),
                Collections.singletonList(perm));

        assertEquals(1, auth.getGrantedPermissions().size());
        assertEquals("/api/resource/**", auth.getGrantedPermissions().get(0).getGrantedPermission());
        assertNull(auth.getMetheaPrincipal());
    }

    @Test
    void constructor_withMetheaPrincipal_setsPrincipal() {
        MetheaPrincipal principal = new MetheaPrincipal();
        principal.setUsername("user");
        principal.setGroupCode("GRP001");

        GrantedPermission perm = () -> "/api/**";
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "user", "pass", Collections.emptyList(),
                Collections.singletonList(perm), principal);

        assertNotNull(auth.getMetheaPrincipal());
        assertEquals("user", auth.getMetheaPrincipal().getUsername());
        assertEquals("GRP001", auth.getMetheaPrincipal().getGroupCode());
        assertEquals(1, auth.getGrantedPermissions().size());
    }

    @Test
    void constructor_withEmptyGrantedPermissions_returnsEmptyList() {
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "user", "pass", Collections.emptyList(),
                Collections.emptyList());

        assertNotNull(auth.getGrantedPermissions());
        assertTrue(auth.getGrantedPermissions().isEmpty());
    }

    @Test
    void constructor_fullFlagConstructor_setsEmptyPermissionsAndNullPrincipal() {
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "user", "pass", true, true, true, true,
                Collections.emptyList());

        assertEquals("user", auth.getUsername());
        assertTrue(auth.getGrantedPermissions().isEmpty());
        assertNull(auth.getMetheaPrincipal());
    }

    @Test
    void constructor_withAuthorities_authoritiesAccessible() {
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_ADMIN");
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "admin", "pass", Collections.singletonList(role));

        assertTrue(auth.getAuthorities().stream()
                .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority())));
    }

    @Test
    void constructor_withMultiplePermissions_allPermissionsPresent() {
        List<GrantedPermission> perms = List.of(
                () -> "/api/users/**",
                () -> "/api/groups/**",
                () -> "/api/roles/**"
        );
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "user", "pass", Collections.emptyList(), perms);

        assertEquals(3, auth.getGrantedPermissions().size());
    }
}
