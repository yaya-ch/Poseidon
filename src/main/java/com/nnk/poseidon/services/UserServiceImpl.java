package com.nnk.poseidon.services;

import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.exceptions.ResourceAlreadyExistsException;
import com.nnk.poseidon.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the UserService interface.
 *
 * @author Yahia CHERIFI
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UserServiceImpl.class);

    /**
     * UserRepository to inject.
     */
    private final UserRepository repository;

    /**
     * UserConverter to inject.
     */
    private final UserConverter converter;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     * @param userConverter  the user converter
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final UserConverter userConverter) {
        this.repository = userRepository;
        this.converter = userConverter;
    }

    /**
     * Find User by id.
     *
     * @param id the id of the User
     * @return the User if found
     */
    @Override
    public UserDTO findById(final Integer id) {
        Optional<User> findUser = repository.findById(id);
        if (findUser.isPresent()) {
            return converter.userEntityToUserDTOConverter(findUser.get());
        } else {
            throw new NoSuchElementException("No user found");
        }
    }

    /**
     * Find all the Users.
     *
     * @return a list of all the Users
     */
    @Override
    public List<UserDTO> findAllUsers() {
        return converter
                .userEntityListToUserDTOListConverter(repository.findAll());
    }

    /**
     * Save a new User.
     *
     * @param user the User to save
     * @return a call to the repo layer
     * @throws ResourceAlreadyExistsException if the username exists
     */
    @Override
    public User saveNewUser(final User user)
            throws ResourceAlreadyExistsException {
        Optional<User> findUserByUserName =
                repository.findByUsername(user.getUsername());
        if (findUserByUserName.isPresent()) {
            LOGGER.error("Failed to save the new User."
                    + " Username {} already exists",
                    findUserByUserName.get().getUsername());
            throw new ResourceAlreadyExistsException("Couldn't save the User."
                    + " Username already exists");
        }
        return repository.save(user);
    }

    /**
     * Update an existing User.
     *
     * @param id   the id of the User to update
     * @param user the new User information
     * @return a call to the repo layer
     */
    @Override
    public User updateUser(final Integer id, final User user) {
        Optional<User> findUser = repository.findById(id);
        if (findUser.isPresent()) {
            user.setId(findUser.get().getId());
            return repository.save(user);
        } else {
            throw new NoSuchElementException("Failed to load user."
                    + " No matching User resource found");
        }
    }

    /**
     * Delete an existing user.
     *
     * @param id the id of the User to delete
     */
    @Override
    public void deleteUser(final Integer id) {
        LOGGER.debug("");
        Optional<User> findUser = repository.findById(id);
        if (findUser.isPresent()) {
            repository.deleteById(id);
        } else {
            LOGGER.error("Failed to delete User {}."
                    + " Resource does not exist", id);
            throw new NoSuchElementException(String.format(
                    "Failed to delete user %s. No matching resources found",
                    id));
        }
    }
}
