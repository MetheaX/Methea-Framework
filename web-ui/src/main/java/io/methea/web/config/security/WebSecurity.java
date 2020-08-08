package io.methea.web.config.security;

import io.methea.service.auth.CustomAuthenticationService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Author : DKSilverX
 * Date : 18/06/2020
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationService customAuthenticationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(CustomAuthenticationService customAuthenticationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customAuthenticationService = customAuthenticationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customAuthenticationService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().antMatcher("/**").authorizeRequests()
                .antMatchers("/login/**", "/logout/**", "/access-denied/**", "/resources/**").permitAll()
                .antMatchers("/**").authenticated().and()
                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password").and()
                .addFilter(new MetheaAuthorizationFilter(authenticationManager()));
    }
}
