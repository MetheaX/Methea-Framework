package com.metheax.sena.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
@SpringBootApplication(scanBasePackages = {"com.metheax.sena"})
@EnableJpaRepositories(basePackages =  {"com.metheax.sena"})
@EntityScan(basePackages = {"com.metheax.sena"})
@ComponentScan(basePackages = {"com.metheax.sena"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
