package io.github.metheax.web.config.security;

import io.github.metheax.service.CustomAuthenticationService;
import io.github.metheax.utils.auth.MetheaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Author : DKSilverX
 * Date : 18/06/2020
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationService customAuthenticationService;
    private final MetheaPasswordEncoder encoder;

    public WebSecurity(CustomAuthenticationService customAuthenticationService, MetheaPasswordEncoder encoder) {
        this.customAuthenticationService = customAuthenticationService;
        this.encoder = encoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customAuthenticationService).passwordEncoder(encoder);
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
