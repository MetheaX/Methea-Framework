package io.github.metheax.config.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SecurityAuditorAwareTest {

    @Test
    void getCurrentAuditor_noSession_returnsSystem() {
        SecurityAuditorAware auditor = new SecurityAuditorAware();
        MockHttpServletRequest request = new MockHttpServletRequest();
        ReflectionTestUtils.setField(auditor, "request", request);

        Optional<String> result = auditor.getCurrentAuditor();

        assertTrue(result.isPresent());
        assertEquals("System", result.get());
    }
}
