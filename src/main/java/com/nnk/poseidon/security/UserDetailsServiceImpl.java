package com.nnk.poseidon.security;

import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type UserDetailsService.
 * This class uses the UserDetails to load correct users from db
 *
 * @author Yahia CHERIFI
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * UserRepository to inject.
     */
    private final UserRepository repository;

    /**
     * Instantiates a new User details service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.repository = userRepository;
    }

    /**
     * This method allows loading users by their usernames from database.
     * It is used by the security layer for authenticating users
     * @param userName the user's username
     * @return a new instance of the UserDetails
     * or throws an exception if no matching username found in db
     */
    @Override
    public UserDetails loadUserByUsername(final String userName) {
        Optional<User> user = repository.findByUsername(userName);
        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw new UsernameNotFoundException("Not found");
        }
    }
}
