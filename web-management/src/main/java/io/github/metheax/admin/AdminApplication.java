package io.github.metheax.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author : DKSilverX
 * Date : 08/08/2020
 */
@SpringBootApplication(scanBasePackages = {"io.methea"})
@EnableJpaRepositories(basePackages =  {"io.methea"})
@EntityScan(basePackages = {"io.methea"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
