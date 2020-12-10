package com.nnk.poseidon.security;

import com.nnk.poseidon.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type UserDetails.
 * This class provides core user information.
 * which are used by the UserDetailsServiceImpl to load users from db
 *
 * @author Yahia CHERIFI
 */

public class UserDetailsImpl implements UserDetails {

    /**
     * the user's username.
     */
    private final String username;

    /**
     * the user's password.
     */
    private final String password;

    /**
     * the user's list of authorities(roles).
     */
    private final List<GrantedAuthority> authorities;

    /**
     * Instantiates a new User details.
     *
     * @param user the user
     */
    public UserDetailsImpl(final User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * getter for authorities.
     * @return the user's authorities(roles)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * getter for password.
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * getter for username.
     * @return true
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * a boolean that indicates if the account is not expired.
     * THIS BOOLEAN RETURNS ALWAYS TRUE IN THIS DEV ENVIRONMENT
     * THIS FEATURE SHOULD BE IMPLEMENTED IN PRODUCTION ENVIRONMENT
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * a boolean that indicates if the account is not locked.
     * THIS BOOLEAN RETURNS ALWAYS TRUE IN THIS DEV ENVIRONMENT
     * THIS FEATURE SHOULD BE IMPLEMENTED IN PRODUCTION ENVIRONMENT
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * a boolean that indicates if the credentials are not expired.
     * THIS BOOLEAN RETURNS ALWAYS TRUE IN THIS DEV ENVIRONMENT
     * THIS FEATURE SHOULD BE IMPLEMENTED IN PRODUCTION ENVIRONMENT
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * a boolean that indicates if the user is enabled or not.
     * THIS BOOLEAN RETURNS ALWAYS TRUE IN THIS DEV ENVIRONMENT
     * THIS FEATURE SHOULD BE IMPLEMENTED IN PRODUCTION ENVIRONMENT
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
