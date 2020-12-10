package com.nnk.poseidon.security;

import com.nnk.poseidon.constants.ConstantNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Poseidon security config.
 * This class groups different security configuration rules.
 *
 * @author Yahia CHERIFI
 */
@Configuration
@EnableWebSecurity
public class PoseidonSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * UserDetailsService to inject.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Instantiates a new Poseidon security config.
     *
     * @param pUserDetailsService the UserDetailsService
     */
    @Autowired
    public PoseidonSecurityConfig(
            @Qualifier("userDetailsServiceImpl")
            final UserDetailsService pUserDetailsService) {
        this.userDetailsService = pUserDetailsService;
    }

    /**
     * Allows building UserDetailsService based authentication.
     * @param auth AuthenticationManagerBuilder
     * @throws Exception if an error occurs when building
     * the UserDetailsService based authentication
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    /**
     * Configuring web based security for specific http requests & urls.
     * Defines the different authorizations on the different urls
     * @param http HttpSecurity
     * @throws Exception if any problem occurs
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**", "/app/**",
                        "/user/**", "/**/delete/**").hasRole("ADMIN")
                .antMatchers("/bidList/**", "/curvePoint/**",
                        "/rating/**", "/ruleName/**", "/trade/**")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/api/**", "/css/**").permitAll()
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
