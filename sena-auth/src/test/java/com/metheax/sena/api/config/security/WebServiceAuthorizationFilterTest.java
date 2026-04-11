package com.metheax.sena.api.config.security;

import com.metheax.sena.api.service.MetheaAuthenticationService;
import com.metheax.sena.config.security.GrantedPermission;
import com.metheax.sena.config.security.MetheaPrincipal;
import com.metheax.sena.config.security.PrincipalAuthentication;
import com.metheax.sena.constant.MetheaConstant;
import com.metheax.sena.domain.entity.PublicPermission;
import com.metheax.sena.domain.entity.Resource;
import com.metheax.sena.repository.WhiteURIPermissionRepository;
import com.metheax.sena.service.KeyStoreService;
import com.metheax.sena.utils.auth.JwtUtil;
import com.metheax.sena.utils.auth.RsaKeyGenerate;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebServiceAuthorizationFilterTest {

    @Mock
    private MetheaAuthenticationService authService;
    @Mock
    private KeyStoreService keyStoreService;
    @Mock
    private WhiteURIPermissionRepository whiteURIRepo;
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private FilterChain filterChain;

    private WebServiceAuthorizationFilter filter;
    private static KeyPair tokenKeyPair;

    private static final String TEST_SESSION_ID = "test-session-abc123";
    // Subject stored in token = username + ":" + TEST_SESSION_ID
    private static final String TOKEN_SUBJECT_TESTUSER = "testuser:" + TEST_SESSION_ID;

    @BeforeAll
    static void generateKeys() {
        tokenKeyPair = new RsaKeyGenerate().createRsa(2048);
    }

    @BeforeEach
    void setUp() {
        filter = new WebServiceAuthorizationFilter(authService, authManager, keyStoreService, whiteURIRepo);

        // JwtUtil.encodeToken reads the session ID from RequestContextHolder
        MockHttpServletRequest contextReq = new MockHttpServletRequest();
        contextReq.setSession(new MockHttpSession(null, TEST_SESSION_ID));
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(contextReq));
    }

    @AfterEach
    void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    // ---- no token, no whitelist → chain called ----

    @Test
    void doFilter_noTokenNoWhitelist_chainCalled() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/resource");
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        verify(filterChain).doFilter(req, res);
        assertEquals(200, res.getStatus());
    }

    // ---- /auth/** URI skips token validation ----

    @Test
    void doFilter_authUri_tokenValidationSkipped_chainCalled() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());

        MockHttpServletRequest req = new MockHttpServletRequest("POST", "/auth/token");
        req.addHeader(SecurityConstants.HEADER_STRING, "Bearer sometoken");
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        verify(filterChain).doFilter(req, res);
        verify(keyStoreService, never()).loadTokenKeyPair();
    }

    // ---- invalid JWT → 401 ----

    @Test
    void doFilter_invalidToken_returns401() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/resource");
        req.addHeader(SecurityConstants.HEADER_STRING, "Bearer this-is-not-a-valid-jwt");
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        assertEquals(401, res.getStatus());
        verify(filterChain, never()).doFilter(any(), any());
    }

    // ---- valid token but session revoked → 401 ----

    @Test
    void doFilter_validTokenRevokedSession_returns401() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        String token = encodeToken("testuser");
        when(authService.loadUserByUsername("testuser")).thenReturn(buildAuth("testuser", Collections.emptyList()));
        when(authService.validateUserRevokedToken(TOKEN_SUBJECT_TESTUSER)).thenReturn(true);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/resource");
        req.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        assertEquals(401, res.getStatus());
        verify(filterChain, never()).doFilter(any(), any());
    }

    // ---- valid token with matching permission → chain called ----

    @Test
    void doFilter_validTokenWithPermission_chainCalled() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        String token = encodeToken("testuser");
        List<GrantedPermission> perms = Collections.singletonList(() -> "/api/resource/**");
        when(authService.loadUserByUsername("testuser")).thenReturn(buildAuth("testuser", perms));
        when(authService.validateUserRevokedToken(TOKEN_SUBJECT_TESTUSER)).thenReturn(false);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/resource/list");
        req.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        verify(filterChain).doFilter(req, res);
    }

    // ---- valid token, no permission for URI → 401 ----

    @Test
    void doFilter_validTokenNoMatchingPermission_returns401() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        String token = encodeToken("testuser");
        when(authService.loadUserByUsername("testuser")).thenReturn(buildAuth("testuser", Collections.emptyList()));
        when(authService.validateUserRevokedToken(TOKEN_SUBJECT_TESTUSER)).thenReturn(false);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/secured");
        req.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        assertEquals(401, res.getStatus());
        verify(filterChain, never()).doFilter(any(), any());
    }

    // ---- exact URI permission match → chain called ----

    @Test
    void doFilter_validTokenExactUriPermission_chainCalled() throws Exception {
        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS)).thenReturn(Collections.emptyList());
        when(keyStoreService.loadTokenKeyPair()).thenReturn(tokenKeyPair);

        String token = encodeToken("testuser");
        List<GrantedPermission> perms = Collections.singletonList(() -> "/api/health");
        when(authService.loadUserByUsername("testuser")).thenReturn(buildAuth("testuser", perms));
        when(authService.validateUserRevokedToken(TOKEN_SUBJECT_TESTUSER)).thenReturn(false);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/health");
        req.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        verify(filterChain).doFilter(req, res);
    }

    // ---- whitelisted URI with allowed method → public user context set ----

    @Test
    void doFilter_whitelistedUriAllowedMethod_setsPublicUserAndCallsChain() throws Exception {
        Resource resource = new Resource();
        resource.setResourceName("/api/**");

        PublicPermission whitePerm = new PublicPermission();
        whitePerm.setResource(resource);
        whitePerm.setAllowedMethod(Arrays.asList("GET", "POST"));

        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS))
                .thenReturn(Collections.singletonList(whitePerm));

        PrincipalAuthentication publicAuth = new PrincipalAuthentication(
                MetheaConstant.PUBLIC_USER, "", Collections.emptyList());
        when(authService.loadUserByUsername(MetheaConstant.PUBLIC_USER)).thenReturn(publicAuth);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/public/data");
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        verify(filterChain).doFilter(req, res);
        verify(authService).loadUserByUsername(MetheaConstant.PUBLIC_USER);
    }

    // ---- whitelisted URI but HTTP method not allowed → not treated as public ----

    @Test
    void doFilter_whitelistedUriDisallowedMethod_noPublicUserSet() throws Exception {
        Resource resource = new Resource();
        resource.setResourceName("/api/**");

        PublicPermission whitePerm = new PublicPermission();
        whitePerm.setResource(resource);
        whitePerm.setAllowedMethod(Collections.singletonList("POST")); // only POST

        when(whiteURIRepo.findAllByStatus(MetheaConstant.ACTIVE_STATUS))
                .thenReturn(Collections.singletonList(whitePerm));

        // GET request with no token → method is not in whitelist, no public user set
        // No token → token block skipped; authentication is null → chain called
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/secure");
        MockHttpServletResponse res = new MockHttpServletResponse();

        filter.doFilter(req, res, filterChain);

        verify(filterChain).doFilter(req, res);
        verify(authService, never()).loadUserByUsername(MetheaConstant.PUBLIC_USER);
    }

    // ---- helpers ----

    private String encodeToken(String username) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 30);
        return JwtUtil.encodeToken(tokenKeyPair.getPublic(), username, "http://localhost", cal)
                .get(MetheaConstant.JWT_TOKEN);
    }

    private PrincipalAuthentication buildAuth(String username, List<GrantedPermission> perms) {
        MetheaPrincipal principal = new MetheaPrincipal();
        principal.setUsername(username);
        principal.setGroupCode("GRP001");
        return new PrincipalAuthentication(username, "pass",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                perms, principal);
    }
}
