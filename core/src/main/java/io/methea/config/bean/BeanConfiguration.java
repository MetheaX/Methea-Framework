package io.methea.config.bean;

import io.methea.constant.MConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
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

    private static Logger log = LoggerFactory.getLogger(BeanConfiguration.class);

    private final Environment env;

    @Inject
    public BeanConfiguration(@Lazy Environment env) {
        this.env = env;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncodingConfigurer() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplateConfigurer() {
        return new RestTemplate();
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        String coreTemplate = env.getProperty(MConstant.CORE_TEMPLATE_KEY);
        if (StringUtils.isNotEmpty(coreTemplate)) {
            String clientTemplate = env.getProperty(MConstant.CLIENT_TEMPLATE_KEY);
            if (StringUtils.isNotEmpty(clientTemplate)) {
                coreTemplate = coreTemplate.concat(MConstant.COMMA).concat(clientTemplate);
            }
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setDefaultEncoding("UTF-8");
            configurer.setTemplateLoaderPaths(coreTemplate.split(MConstant.COMMA));
            return configurer;
        }
        log.info(">>>>> Failed to load core template - " + MConstant.CORE_TEMPLATE_KEY);
        return null;
    }
}
