package com.nnk.poseidon.config;

import com.nnk.poseidon.constants.ConstantNumbers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Poseidon security config.
 *
 * @author Yahia CHERIFI This class groups different security configurations.
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

    /**
     * Password encoder bean.
     * Used to encode users' passwords
     *
     * @return a new instance of the BcryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(ConstantNumbers.FIFTEEN);
    }
}
