package com.metheax.sena.api.service;

import com.metheax.sena.api.domain.RefreshTokenPayload;
import com.metheax.sena.api.domain.Token;
import com.metheax.sena.utils.auth.JwtUtil;
import com.metheax.sena.api.domain.RequestTokenPayload;
import com.metheax.sena.api.domain.RevokeTokenPayload;
import com.metheax.sena.config.security.PrincipalAuthentication;
import com.metheax.sena.constant.MetheaConstant;
import com.metheax.sena.domain.entity.Group;
import com.metheax.sena.domain.entity.SessionManagement;
import com.metheax.sena.domain.entity.User;
import com.metheax.sena.domain.view.PermissionView;
import com.metheax.sena.exception.InvalidClientSecretException;
import com.metheax.sena.repository.PermissionRepository;
import com.metheax.sena.repository.SessionManagementRepository;
import com.metheax.sena.repository.UserRepository;
import com.metheax.sena.service.KeyStoreService;
import com.metheax.sena.utils.auth.MetheaPasswordEncoder;
import com.metheax.sena.utils.auth.RsaKeyGenerate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.KeyPair;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetheaAuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private SessionManagementRepository sessionManagementRepository;

    @Mock
    private MetheaPasswordEncoder encoder;

    @Mock
    private KeyStoreService keyStoreService;

    @InjectMocks
    private MetheaAuthenticationService service;

    private static KeyPair tokenKeyPair;
    private static KeyPair refreshKeyPair;

    private MockHttpServletRequest mockRequest;

    @BeforeAll
    static void setUpKeys() {
        RsaKeyGenerate gen = new RsaKeyGenerate();
        tokenKeyPair = gen.createRsa(2048);
        refreshKeyPair = gen.createRsa(2048);
    }

    @BeforeEach
    void setUpRequestContext() {
        mockRequest = new MockHttpServletRequest();
        mockRequest.setRequestURI("/api/test");
        mockRequest.setServerName("localhost");
        mockRequest.setServerPort(8080);
        MockHttpSession session = new MockHttpSession(null, "test-session-id");
        mockRequest.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
    }

    @AfterEach
    void tearDownRequestContext() {
        RequestContextHolder.resetRequestAttributes();
    }

    // ---- loadUserByUsername ----

    @Test
    void loadUserByUsername_userExists_returnsPrincipalAuthentication() {
        User user = buildMockUser("testuser");
        when(userRepository.findByUsernameAndStatus("testuser", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(user);
        when(permissionRepository.getByQuery(any(), eq(PermissionView.class)))
                .thenReturn(Collections.emptyList());

        PrincipalAuthentication result = service.loadUserByUsername("testuser");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void loadUserByUsername_userNotFound_returnsNull() {
        when(userRepository.findByUsernameAndStatus("unknown", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(null);

        PrincipalAuthentication result = service.loadUserByUsername("unknown");

        assertNull(result);
    }

    @Test
    void loadUserByUsername_userWithPermissions_permissionsLoaded() {
        User user = buildMockUser("testuser");
        PermissionView pv = new PermissionView("/api/resource/**");
        when(userRepository.findByUsernameAndStatus("testuser", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(user);
        when(permissionRepository.getByQuery(any(), eq(PermissionView.class)))
                .thenReturn(Collections.singletonList(pv));

        PrincipalAuthentication result = service.loadUserByUsername("testuser");

        assertNotNull(result);
        assertEquals(1, result.getGrantedPermissions().size());
        assertEquals("/api/resource/**", result.getGrantedPermissions().get(0).getGrantedPermission());
    }

    // ---- validateUserRevokedToken ----

    @Test
    void validateUserRevokedToken_noActiveSession_returnsTrue() {
        when(sessionManagementRepository.findAllByUserLoginIdAndSessionIdAndIsLogout("user", "sess1", false))
                .thenReturn(Collections.emptyList());

        assertTrue(service.validateUserRevokedToken("user:sess1"));
    }

    @Test
    void validateUserRevokedToken_activeSessionExists_returnsFalse() {
        SessionManagement session = new SessionManagement();
        when(sessionManagementRepository.findAllByUserLoginIdAndSessionIdAndIsLogout("user", "sess1", false))
                .thenReturn(Collections.singletonList(session));

        assertFalse(service.validateUserRevokedToken("user:sess1"));
    }

    // ---- generateTokenFromUser ----

    @Test
    void generateTokenFromUser_validCredentials_returnsToken() {
        User user = buildMockUser("testuser");
        when(userRepository.findByUsernameAndStatus("testuser", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(user);
        when(encoder.matches(any(), any())).thenReturn(true);
        when(permissionRepository.getByQuery(any(), eq(PermissionView.class)))
                .thenReturn(Collections.emptyList());
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);
        when(keyStoreService.loadRefreshTokenKeyPair()).thenReturn(refreshKeyPair);
        when(sessionManagementRepository.save(any())).thenReturn(new SessionManagement());

        RequestTokenPayload payload = new RequestTokenPayload();
        payload.setUsername("testuser");
        payload.setPassword("password");

        var token = service.generateTokenFromUser(payload, mockRequest);

        assertNotNull(token);
        assertNotNull(token.getAccessToken());
        assertFalse(token.getAccessToken().isEmpty());
    }

    @Test
    void generateTokenFromUser_userNotFound_returnsNull() {
        when(userRepository.findByUsernameAndStatus("unknown", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(null);

        RequestTokenPayload payload = new RequestTokenPayload();
        payload.setUsername("unknown");
        payload.setPassword("password");

        assertNull(service.generateTokenFromUser(payload, mockRequest));
    }

    @Test
    void generateTokenFromUser_wrongPassword_returnsNull() {
        User user = buildMockUser("testuser");
        when(userRepository.findByUsernameAndStatus("testuser", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(user);
        when(encoder.matches(any(), any())).thenReturn(false);

        RequestTokenPayload payload = new RequestTokenPayload();
        payload.setUsername("testuser");
        payload.setPassword("wrongpassword");

        assertNull(service.generateTokenFromUser(payload, mockRequest));
    }

    // ---- revokeAccessToken ----

    @Test
    void revokeAccessToken_validToken_marksSessionsAsLogout() {
        // Encode a real token so we can decode it
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);
        SessionManagement session = new SessionManagement();
        session.setLogout(false);
        when(sessionManagementRepository.findAllByUserLoginIdAndSessionIdAndIsLogout(
                eq("testuser"), eq("test-session-id"), eq(false)))
                .thenReturn(Collections.singletonList(session));
        when(sessionManagementRepository.save(any())).thenReturn(session);

        // Generate a real token to use in revoke
        JwtUtil.encodeToken(
                tokenKeyPair.getPublic(), "testuser", "http://localhost", validCalendar());
        String rawToken = JwtUtil
                .encodeToken(tokenKeyPair.getPublic(), "testuser", "http://localhost", validCalendar())
                .get(MetheaConstant.JWT_TOKEN);

        RevokeTokenPayload payload = new RevokeTokenPayload();
        payload.setUsername("testuser");
        payload.setToken(rawToken);

        service.revokeAccessToken(payload, mockRequest);

        assertTrue(session.isLogout(), "Session should be marked as logout");
        verify(sessionManagementRepository, atLeastOnce()).save(session);
    }

    @Test
    void revokeAccessToken_usernameMismatch_throwsInvalidClientSecretException() {
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        String rawToken = JwtUtil
                .encodeToken(tokenKeyPair.getPublic(), "actualuser", "http://localhost", validCalendar())
                .get(MetheaConstant.JWT_TOKEN);

        RevokeTokenPayload payload = new RevokeTokenPayload();
        payload.setUsername("differentuser");
        payload.setToken(rawToken);

        assertThrows(InvalidClientSecretException.class,
                () -> service.revokeAccessToken(payload, mockRequest));
    }

    // ---- generateTokenFromRefreshToken ----

    @Test
    void generateTokenFromRefreshToken_invalidToken_returnsNull() {
        when(keyStoreService.loadRefreshTokenKeyPair()).thenReturn(refreshKeyPair);

        RefreshTokenPayload payload = new RefreshTokenPayload();
        payload.setRefreshToken("not-a-valid-jwt");

        assertNull(service.generateTokenFromRefreshToken(payload, mockRequest));
    }

    @Test
    void generateTokenFromRefreshToken_expiredToken_returnsNull() {
        when(keyStoreService.loadRefreshTokenKeyPair()).thenReturn(refreshKeyPair);

        java.util.Calendar expiredCal = java.util.Calendar.getInstance();
        expiredCal.add(java.util.Calendar.SECOND, -1);
        String expiredToken = JwtUtil
                .encodeToken(refreshKeyPair.getPublic(), "testuser", "http://localhost", expiredCal)
                .get(MetheaConstant.JWT_TOKEN);

        RefreshTokenPayload payload = new RefreshTokenPayload();
        payload.setRefreshToken(expiredToken);

        assertNull(service.generateTokenFromRefreshToken(payload, mockRequest));
    }

    @Test
    void generateTokenFromRefreshToken_revokedSession_returnsNull() {
        when(keyStoreService.loadRefreshTokenKeyPair()).thenReturn(refreshKeyPair);

        String rawToken = JwtUtil
                .encodeToken(refreshKeyPair.getPublic(), "testuser", "http://localhost", validCalendar())
                .get(MetheaConstant.JWT_TOKEN);

        // Session is revoked (empty list = revoked)
        when(sessionManagementRepository.findAllByUserLoginIdAndSessionIdAndIsLogout(
                eq("testuser"), anyString(), eq(false)))
                .thenReturn(Collections.emptyList());

        RefreshTokenPayload payload = new RefreshTokenPayload();
        payload.setRefreshToken(rawToken);

        assertNull(service.generateTokenFromRefreshToken(payload, mockRequest));
    }

    @Test
    void generateTokenFromRefreshToken_userNotFound_returnsNull() {
        when(keyStoreService.loadRefreshTokenKeyPair()).thenReturn(refreshKeyPair);

        String rawToken = JwtUtil
                .encodeToken(refreshKeyPair.getPublic(), "testuser", "http://localhost", validCalendar())
                .get(MetheaConstant.JWT_TOKEN);

        // Session is active (not revoked)
        when(sessionManagementRepository.findAllByUserLoginIdAndSessionIdAndIsLogout(
                eq("testuser"), anyString(), eq(false)))
                .thenReturn(Collections.singletonList(new SessionManagement()));

        // User not found
        when(userRepository.findByUsernameAndStatus("testuser", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(null);

        RefreshTokenPayload payload = new RefreshTokenPayload();
        payload.setRefreshToken(rawToken);

        assertNull(service.generateTokenFromRefreshToken(payload, mockRequest));
    }

    @Test
    void generateTokenFromRefreshToken_validToken_returnsNewAccessToken() {
        when(keyStoreService.loadRefreshTokenKeyPair()).thenReturn(refreshKeyPair);
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        String rawToken = JwtUtil
                .encodeToken(refreshKeyPair.getPublic(), "testuser", "http://localhost", validCalendar())
                .get(MetheaConstant.JWT_TOKEN);

        // Session is active
        when(sessionManagementRepository.findAllByUserLoginIdAndSessionIdAndIsLogout(
                eq("testuser"), anyString(), eq(false)))
                .thenReturn(Collections.singletonList(new SessionManagement()));

        User user = buildMockUser("testuser");
        when(userRepository.findByUsernameAndStatus("testuser", MetheaConstant.ACTIVE_STATUS))
                .thenReturn(user);
        when(permissionRepository.getByQuery(any(), eq(PermissionView.class)))
                .thenReturn(Collections.emptyList());
        when(sessionManagementRepository.save(any())).thenReturn(new SessionManagement());

        RefreshTokenPayload payload = new RefreshTokenPayload();
        payload.setRefreshToken(rawToken);

        Token token = service.generateTokenFromRefreshToken(payload, mockRequest);

        assertNotNull(token);
        assertNotNull(token.getAccessToken());
        assertFalse(token.getAccessToken().isEmpty());
        // Refresh token is NOT re-generated when isRefreshToken=true
        assertNull(token.getRefreshToken());
    }

    // ---- helpers ----

    private User buildMockUser(String username) {
        User user = mock(User.class);
        Group group = new Group();
        group.setGroupCode("GRP001");
        // getPassword() is always called (in encoder.matches check)
        when(user.getPassword()).thenReturn(MetheaConstant.ARGON_PREFIX + "fakesalt$fakehashvalue");
        // The following are only called when buildPrincipal is reached (password check passes)
        lenient().when(user.getUsername()).thenReturn(username);
        lenient().when(user.getGroup()).thenReturn(group);
        lenient().when(user.getRoles()).thenReturn(Collections.emptyList());
        return user;
    }

    private java.util.Calendar validCalendar() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MINUTE, 30);
        return cal;
    }
}
