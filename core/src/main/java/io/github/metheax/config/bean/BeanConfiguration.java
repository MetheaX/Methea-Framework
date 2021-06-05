package io.github.metheax.config.bean;

import io.github.metheax.constant.MetheaConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
@Configuration
public class BeanConfiguration {

    private final Environment env;

    @Inject
    public BeanConfiguration(@Lazy Environment env) {
        this.env = env;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncodingConfigurer() {
        return new BCryptPasswordEncoder();
    }

    public Argon2PasswordEncoder argon2PasswordEncoder() {
        return new Argon2PasswordEncoder(MetheaConstant.SALT_LENGTH, MetheaConstant.HASH_LENGTH, MetheaConstant.PARALLELISM,
                MetheaConstant.MEMORY, MetheaConstant.ITERATIONS);
    }

    @Bean
    public RestTemplate restTemplateConfigurer() {
        return new RestTemplate();
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        String coreTemplate = env.getProperty(MetheaConstant.CORE_TEMPLATE_KEY);
        if (StringUtils.isNotEmpty(coreTemplate)) {
            String clientTemplate = env.getProperty(MetheaConstant.CLIENT_TEMPLATE_KEY);
            if (StringUtils.isNotEmpty(clientTemplate)) {
                coreTemplate = coreTemplate.concat(MetheaConstant.COMMA).concat(clientTemplate);
            }
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setDefaultEncoding("UTF-8");
            configurer.setTemplateLoaderPaths(coreTemplate.split(MetheaConstant.COMMA));
            return configurer;
        }
        return null;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("message");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
