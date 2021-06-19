package io.github.metheax.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author : Kuylim Tith
 * Date : 05/05/2020
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef="auditorAwareProvider")
public class JPAConfiguration {
    @Bean
    public AuditorAware<String> auditorAwareProvider() {
        return new SecurityAuditorAware();
    }
}
