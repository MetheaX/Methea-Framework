package io.github.metheax.config.environment;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Author : DKSilverX
 * Date : 9/8/2019
 */
@Configuration
@PropertySource({"classpath:environment.properties", "classpath:core-environment.properties"})
public class EnvironmentConfiguration {
}
