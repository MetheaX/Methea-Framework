package io.github.metheax.config.bean;

import io.github.metheax.constant.MetheaConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * Author : Kuylim Tith
 * Date : 9/8/2019
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncodingConfigurer() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Argon2PasswordEncoder argon2PasswordEncoder() {
        return new Argon2PasswordEncoder(MetheaConstant.SALT_LENGTH, MetheaConstant.HASH_LENGTH, MetheaConstant.PARALLELISM,
                MetheaConstant.MEMORY, MetheaConstant.ITERATIONS);
    }

    @Bean
    public RestTemplate restTemplateConfigurer() {
        return new RestTemplate();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("message");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
