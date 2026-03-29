package io.github.metheax.config.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanConfigurationTest {

    private final BeanConfiguration config = new BeanConfiguration();

    @Test
    void bCryptPasswordEncodingConfigurer_returnsNonNull() {
        BCryptPasswordEncoder encoder = config.bCryptPasswordEncodingConfigurer();
        assertNotNull(encoder);
    }

    @Test
    void argon2PasswordEncoder_returnsNonNull() {
        Argon2PasswordEncoder encoder = config.argon2PasswordEncoder();
        assertNotNull(encoder);
    }

    @Test
    void restTemplateConfigurer_returnsNonNull() {
        RestTemplate restTemplate = config.restTemplateConfigurer();
        assertNotNull(restTemplate);
    }

    @Test
    void messageSource_returnsConfiguredSource() {
        ResourceBundleMessageSource source = config.messageSource();
        assertNotNull(source);
        // useCodeAsDefaultMessage is set to true in the impl
        assertNotNull(source.getMessage("any.nonexistent.key", null, java.util.Locale.getDefault()));
    }
}
