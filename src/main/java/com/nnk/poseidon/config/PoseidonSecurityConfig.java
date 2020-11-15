package com.nnk.poseidon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Yahia CHERIFI
 * This class groups different security configurations.
 */

@Configuration
@EnableWebSecurity
public class PoseidonSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Allows configuring web based security for specific http requests.
     * @param http HttpSecurity
     * @throws Exception if any problem occurs
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }
}
