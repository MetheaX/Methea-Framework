package io.github.metheax.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author : Kuylim Tith
 * Date : 08/08/2020
 */
@SpringBootApplication(scanBasePackages = {"io.github.metheax"})
@EnableJpaRepositories(basePackages =  {"io.github.metheax"})
@EntityScan(basePackages = {"io.github.metheax"})
@ComponentScan(basePackages = {"io.github.metheax"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
