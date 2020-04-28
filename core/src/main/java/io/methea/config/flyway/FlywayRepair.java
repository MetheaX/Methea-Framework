package io.methea.config.flyway;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author : DKSilverX
 * Date : 23/04/2020
 */
@Configuration
public class FlywayRepair {
    @Bean
    public FlywayMigrationStrategy repair() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }
}
