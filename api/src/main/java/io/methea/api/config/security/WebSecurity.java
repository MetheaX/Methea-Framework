package io.methea.api.config.security;

import io.methea.api.service.MetheaAuthenticationService;
import io.methea.constant.MConstant;
import io.methea.repository.configuration.permission.WhiteURIPermissionRepository;
import io.methea.repository.webservice.system.SystemCertificateRepository;
import org.springframework.context.annotation.Bean;
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
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final MetheaAuthenticationService metheaAuthenticationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SystemCertificateRepository certificateRepository;
    private final WhiteURIPermissionRepository whiteURIPermissionRepository;
    private final Environment env;

    @Inject
    public WebSecurity(MetheaAuthenticationService service, BCryptPasswordEncoder bCryptPasswordEncoder,
                       SystemCertificateRepository certificateRepository,
                       WhiteURIPermissionRepository whiteURIPermissionRepository, Environment env) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.certificateRepository = certificateRepository;
        this.metheaAuthenticationService = service;
        this.whiteURIPermissionRepository = whiteURIPermissionRepository;
        this.env = env;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(metheaAuthenticationService).passwordEncoder(bCryptPasswordEncoder);
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
                ObjectUtils.isEmpty(env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)) ? SecurityConstants.HEADER_STRING
                        : env.getProperty(MConstant.CLIENT_REQUEST_HEADER_KEY)));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}