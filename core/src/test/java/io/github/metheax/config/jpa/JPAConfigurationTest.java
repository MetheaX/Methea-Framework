package io.github.metheax.config.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JPAConfigurationTest {

    @Test
    void auditorAwareProvider_returnsNonNull() {
        JPAConfiguration config = new JPAConfiguration();
        AuditorAware<String> auditor = config.auditorAwareProvider();
        assertNotNull(auditor);
    }
}
