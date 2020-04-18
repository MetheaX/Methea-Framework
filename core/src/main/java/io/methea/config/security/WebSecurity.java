package io.methea.config.security;

import io.methea.config.jwt.JWTAuthenticationFilter;
import io.methea.config.jwt.JWTAuthorizationFilter;
import io.methea.service.auth.CustomAuthenticationService;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;

/**
 * Author : DKSilverX
 * Date : 21/08/2019
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationService customAuthenticationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Inject
    public WebSecurity(CustomAuthenticationService customAuthenticationService, BCryptPasswordEncoder bCryptPasswordEncoder,
                       Environment env) {
        this.customAuthenticationService = customAuthenticationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/activate-sys/**").permitAll()
                .antMatchers("/auth/token/**", "/login/**", "/access-denied/**", "/resources/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password").and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), env))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), env));
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customAuthenticationService).passwordEncoder(bCryptPasswordEncoder);
    }
}
