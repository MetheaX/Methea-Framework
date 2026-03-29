package io.github.metheax.api.config.security;

import io.github.metheax.api.service.MetheaAuthenticationService;
import io.github.metheax.repository.WhiteURIPermissionRepository;
import io.github.metheax.service.KeyStoreService;
import io.github.metheax.utils.auth.MetheaPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WebSecurityTest {

    @Mock MetheaAuthenticationService authService;
    @Mock MetheaPasswordEncoder encoder;
    @Mock WhiteURIPermissionRepository whiteURIRepo;
    @Mock KeyStoreService keyStoreService;

    private WebSecurity webSecurity;

    @BeforeEach
    void setUp() {
        webSecurity = new WebSecurity(authService, encoder, whiteURIRepo, keyStoreService);
    }

    @Test
    void corsConfigurationSource_returnsUrlBasedSource() {
        CorsConfigurationSource source = webSecurity.corsConfigurationSource();
        assertNotNull(source);
        assertInstanceOf(UrlBasedCorsConfigurationSource.class, source);
    }

    @Test
    void corsConfigurationSource_allowsExpectedMethods() {
        UrlBasedCorsConfigurationSource source =
                (UrlBasedCorsConfigurationSource) webSecurity.corsConfigurationSource();
        CorsConfiguration config = source.getCorsConfiguration(
                new org.springframework.mock.web.MockHttpServletRequest("GET", "/api/test"));
        assertNotNull(config);
        assertTrue(config.getAllowedMethods().containsAll(
                java.util.Arrays.asList("GET", "POST", "PUT", "DELETE")));
    }

    @Test
    void corsConfigurationSource_allowsAllOrigins() {
        UrlBasedCorsConfigurationSource source =
                (UrlBasedCorsConfigurationSource) webSecurity.corsConfigurationSource();
        CorsConfiguration config = source.getCorsConfiguration(
                new org.springframework.mock.web.MockHttpServletRequest("GET", "/any"));
        assertNotNull(config);
        assertTrue(config.getAllowedOrigins().contains("*"));
    }

    @Test
    void corsConfigurationSource_includesAuthorizationHeader() {
        UrlBasedCorsConfigurationSource source =
                (UrlBasedCorsConfigurationSource) webSecurity.corsConfigurationSource();
        CorsConfiguration config = source.getCorsConfiguration(
                new org.springframework.mock.web.MockHttpServletRequest("GET", "/any"));
        assertNotNull(config);
        assertTrue(config.getAllowedHeaders().contains(SecurityConstants.HEADER_STRING));
    }
}
