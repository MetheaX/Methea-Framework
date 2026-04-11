package com.metheax.sena.utils.auth;

import com.metheax.sena.constant.MetheaConstant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.KeyPair;
import java.util.Calendar;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static KeyPair keyPair;
    private static final String TEST_SESSION_ID = "test-session-id-12345";
    private static final String TEST_SUBJECT = "testuser";
    private static final String TEST_ISSUER = "http://localhost:8080";

    @BeforeAll
    static void setUpKeys() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        keyPair = generator.createRsa(2048);
    }

    @BeforeEach
    void setUpRequestContext() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession(null, TEST_SESSION_ID);
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterEach
    void tearDownRequestContext() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void encodeToken_returnsMapWithJwtToken() {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        Map<String, String> result = JwtUtil.encodeToken(keyPair.getPublic(), TEST_SUBJECT, TEST_ISSUER, expiration);

        assertNotNull(result);
        assertTrue(result.containsKey(MetheaConstant.JWT_TOKEN));
        assertFalse(result.get(MetheaConstant.JWT_TOKEN).isEmpty());
    }

    @Test
    void decodeToken_validToken_returnsSubjectWithSessionId() {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        Map<String, String> encoded = JwtUtil.encodeToken(keyPair.getPublic(), TEST_SUBJECT, TEST_ISSUER, expiration);
        String token = encoded.get(MetheaConstant.JWT_TOKEN);

        String subject = JwtUtil.decodeToken(token, keyPair.getPrivate());

        // Subject is "username:sessionId"
        assertEquals(TEST_SUBJECT + MetheaConstant.COLON + TEST_SESSION_ID, subject);
    }

    @Test
    void decodeToken_expiredToken_returnsEmpty() {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.SECOND, -1); // Already expired

        Map<String, String> encoded = JwtUtil.encodeToken(keyPair.getPublic(), TEST_SUBJECT, TEST_ISSUER, expiration);
        String token = encoded.get(MetheaConstant.JWT_TOKEN);

        String subject = JwtUtil.decodeToken(token, keyPair.getPrivate());

        assertEquals("", subject);
    }

    @Test
    void decodeToken_invalidToken_returnsEmpty() {
        String subject = JwtUtil.decodeToken("not-a-valid-token", keyPair.getPrivate());
        assertEquals("", subject);
    }

    @Test
    void decodeToken_emptyToken_returnsEmpty() {
        String subject = JwtUtil.decodeToken("", keyPair.getPrivate());
        assertEquals("", subject);
    }

    @Test
    void decodeToken_wrongPrivateKey_returnsEmpty() {
        RsaKeyGenerate generator = new RsaKeyGenerate();
        KeyPair anotherKeyPair = generator.createRsa(2048);

        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        Map<String, String> encoded = JwtUtil.encodeToken(keyPair.getPublic(), TEST_SUBJECT, TEST_ISSUER, expiration);
        String token = encoded.get(MetheaConstant.JWT_TOKEN);

        // Decoding with the wrong private key should fail
        String subject = JwtUtil.decodeToken(token, anotherKeyPair.getPrivate());
        assertEquals("", subject);
    }
}
