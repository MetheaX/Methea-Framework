package io.methea.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author : DKSilverX
 * Date : 18/08/2019
 */
@SpringBootApplication(scanBasePackages = {"io.methea"})
@EnableJpaRepositories(basePackages =  {"io.methea"})
@EntityScan(basePackages = {"io.methea"})
@ComponentScan(basePackages = {"io.methea"})
public class URMApplication {
    public static void main(String[] args) {
        SpringApplication.run(URMApplication.class, args);
    }
}
