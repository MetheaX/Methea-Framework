package io.methea.config.security;

import io.methea.config.jwt.JWTAuthenticationFilter;
import io.methea.config.jwt.JWTAuthorizationFilter;
import io.methea.config.jwt.JWTConstants;
import io.methea.constant.MConstant;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import io.methea.service.auth.CustomAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;

/**
 * Author : DKSilverX
 * Date : 18/06/2020
 */
@EnableWebSecurity
@Configuration
public class WebSecurity {

    @Order(1)
    @Configuration
    public static class ApiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

        private final CustomAuthenticationService customAuthenticationService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
        private final SystemCertificateRepository certificateRepository;
        private final Environment env;

        @Inject
        public ApiWebSecurityConfigAdapter(CustomAuthenticationService customAuthenticationService,
                                           BCryptPasswordEncoder bCryptPasswordEncoder,
                                           SystemCertificateRepository certificateRepository, Environment env) {
            this.customAuthenticationService = customAuthenticationService;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
            this.certificateRepository = certificateRepository;
            this.env = env;
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(customAuthenticationService).passwordEncoder(bCryptPasswordEncoder);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.cors().and().csrf().disable().antMatcher("/api/**").authorizeRequests()
                    .antMatchers("/auth/token/**", "/login/**").permitAll()
                    .antMatchers("/api/**").authenticated().and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager(), customAuthenticationService, env, certificateRepository))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager(), env, customAuthenticationService, certificateRepository))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            final CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedOrigins(Collections.singletonList("*"));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTION", "DELETE"));
            configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Headers", "x-requested-with",
                    ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)) ? JWTConstants.HEADER_STRING
                            : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }

    @Order(2)
    @Configuration
    public static class FormLoginWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
        private final CustomAuthenticationService customAuthenticationService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
        private final SystemCertificateRepository certificateRepository;
        private final Environment env;

        public FormLoginWebSecurityConfigAdapter(CustomAuthenticationService customAuthenticationService,
                                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                                 SystemCertificateRepository certificateRepository, Environment env) {
            this.customAuthenticationService = customAuthenticationService;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
            this.certificateRepository = certificateRepository;
            this.env = env;
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(customAuthenticationService).passwordEncoder(bCryptPasswordEncoder);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.cors().and().csrf().disable().antMatcher("/**").authorizeRequests()
                    .antMatchers("/auth/token/**", "/login/**", "/access-denied/**", "/resources/**").permitAll()
                    .antMatchers("/**").authenticated().and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("username").passwordParameter("password").and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager(), customAuthenticationService, env, certificateRepository))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager(), env, customAuthenticationService, certificateRepository));
        }
    }
}
