package com.metheax.sena.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilsTest {

    @Test
    void getBaseUrl_noContextPath_returnsSchemeHostPort() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(8080);
        request.setRequestURI("/api/test/resource");
        request.setContextPath("");

        String result = SystemUtils.getBaseUrl(request);

        assertNotNull(result);
        assertTrue(result.contains("localhost"));
        assertFalse(result.contains("/api/test/resource"));
    }

    @Test
    void getBaseUrl_withContextPath_includesContextPath() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("https");
        request.setServerName("example.com");
        request.setServerPort(443);
        request.setContextPath("/myapp");
        request.setRequestURI("/myapp/api/data");

        String result = SystemUtils.getBaseUrl(request);

        assertNotNull(result);
        assertTrue(result.endsWith("/myapp"));
        assertFalse(result.contains("/api/data"));
    }

    @Test
    void getBaseUrl_rootUri_returnsBaseOnly() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("127.0.0.1");
        request.setServerPort(9090);
        request.setRequestURI("/");
        request.setContextPath("");

        String result = SystemUtils.getBaseUrl(request);

        assertNotNull(result);
        assertTrue(result.contains("127.0.0.1"));
    }
}
