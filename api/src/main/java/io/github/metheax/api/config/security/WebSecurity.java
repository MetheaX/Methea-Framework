package io.github.metheax.api.config.security;

import io.github.metheax.api.service.MetheaAuthenticationService;
import io.github.metheax.repository.WhiteURIPermissionRepository;
import io.github.metheax.repository.SystemCertificateRepository;
import io.github.metheax.utils.auth.MetheaPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;

/**
 * Author : Kuylim Tith
 * Date : 18/06/2020
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final MetheaAuthenticationService metheaAuthenticationService;
    private final MetheaPasswordEncoder encoder;
    private final SystemCertificateRepository certificateRepository;
    private final WhiteURIPermissionRepository whiteURIPermissionRepository;

    @Inject
    public WebSecurity(MetheaAuthenticationService service, MetheaPasswordEncoder encoder,
                       SystemCertificateRepository certificateRepository,
                       WhiteURIPermissionRepository whiteURIPermissionRepository) {
        this.encoder = encoder;
        this.certificateRepository = certificateRepository;
        this.metheaAuthenticationService = service;
        this.whiteURIPermissionRepository = whiteURIPermissionRepository;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(metheaAuthenticationService).passwordEncoder(encoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/auth/**", "/unauthorized-access").permitAll()
                .antMatchers("/**").authenticated().and()
                .addFilter(new WebServiceAuthorizationFilter(metheaAuthenticationService, authenticationManager(),
                        certificateRepository, whiteURIPermissionRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.headers().frameOptions().sameOrigin();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTION", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Headers", "x-requested-with",
                SecurityConstants.HEADER_STRING));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
