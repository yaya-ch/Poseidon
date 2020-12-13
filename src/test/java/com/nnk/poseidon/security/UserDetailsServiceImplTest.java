package com.nnk.poseidon.security;

import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.repositories.UserRepository;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Yahia CHERIFI
 */

@Tag("security")
@SpringJUnitConfig(UserDetailsServiceImpl.class)
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        DataLoader dataLoader = new DataLoader();
        user = dataLoader.setUser();
    }

    @Test
    void givenValidUserName_whenLoadUserByUsername_thenCorrectUserShouldBeReturned() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.of(user));
        UserDetails result = userDetailsService.loadUserByUsername("correctUserName");
        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()), result.getAuthorities());
        assertTrue(result.isAccountNonExpired());
        assertTrue(result.isAccountNonLocked());
        assertTrue(result.isCredentialsNonExpired());
        assertTrue(result.isEnabled());

    }

    @Test
    void givenInvalidUserName_whenLoadUserByUsername_thenExceptionShouldBeThrown() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("wrong"));
    }
}