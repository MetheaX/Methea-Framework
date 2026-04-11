package com.metheax.sena.utils;

import com.metheax.sena.config.security.MetheaPrincipal;
import com.metheax.sena.config.security.PrincipalAuthentication;
import com.metheax.sena.constant.MetheaConstant;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrincipalUtilsTest {

    @Test
    void getUserLoginId_noSecurityContext_returnsSystem() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertEquals("System", PrincipalUtils.getUserLoginId(request));
    }

    @Test
    void getUserLoginId_emptySecurityContext_returnsSystem() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        // Session exists but no SPRING_SECURITY_CONTEXT attribute
        request.setSession(session);
        assertEquals("System", PrincipalUtils.getUserLoginId(request));
    }

    @Test
    void getUserLoginId_withAuthenticatedUser_returnsUsername() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();

        MetheaPrincipal principal = buildPrincipal("alice", "GRP001");
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "alice", "pass", Collections.emptyList(), Collections.emptyList(), principal);

        SecurityContextImpl ctx = new SecurityContextImpl(
                new UsernamePasswordAuthenticationToken(auth, null));
        session.setAttribute(MetheaConstant.SPRING_SECURITY_CONTEXT, ctx);
        request.setSession(session);

        assertEquals("alice", PrincipalUtils.getUserLoginId(request));
    }

    @Test
    void getLoginGroupCode_noSecurityContext_returnsSystem() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertEquals("System", PrincipalUtils.getLoginGroupCode(request));
    }

    @Test
    void getLoginGroupCode_withAuthenticatedUser_returnsGroupCode() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();

        MetheaPrincipal principal = buildPrincipal("bob", "ADMIN_GRP");
        PrincipalAuthentication auth = new PrincipalAuthentication(
                "bob", "pass", Collections.emptyList(), Collections.emptyList(), principal);

        SecurityContextImpl ctx = new SecurityContextImpl(
                new UsernamePasswordAuthenticationToken(auth, null));
        session.setAttribute(MetheaConstant.SPRING_SECURITY_CONTEXT, ctx);
        request.setSession(session);

        assertEquals("ADMIN_GRP", PrincipalUtils.getLoginGroupCode(request));
    }

    @Test
    void getUserLoginId_nullAuthenticationInContext_returnsSystem() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();

        // SecurityContextImpl with no authentication
        SecurityContextImpl ctx = new SecurityContextImpl();
        session.setAttribute(MetheaConstant.SPRING_SECURITY_CONTEXT, ctx);
        request.setSession(session);

        assertEquals("System", PrincipalUtils.getUserLoginId(request));
    }

    private MetheaPrincipal buildPrincipal(String username, String groupCode) {
        MetheaPrincipal principal = new MetheaPrincipal();
        principal.setUsername(username);
        principal.setGroupCode(groupCode);
        return principal;
    }
}
