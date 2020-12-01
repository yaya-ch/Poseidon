package com.nnk.poseidon.controllers.api;

import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.exceptions.ResourceAlreadyExistsException;
import com.nnk.poseidon.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller provides api endpoints.
 * that allow CRUD operations on User
 *
 * @author Yahia CHERIFI
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UserApiController.class);

    /**
     * UserService to inject.
     */
    private final UserService service;

    /**
     * UserConverter to inject.
     */
    private final UserConverter converter;

    /**
     * Password encoder to inject.
     */
    private final PasswordEncoder encoder;

    /**
     * Instantiates a new User api controller.
     *
     * @param userService     the user service
     * @param userConverter   the user converter
     * @param passwordEncoder the password encoder
     */
    @Autowired
    public UserApiController(final UserService userService,
                             final UserConverter userConverter,
                             final PasswordEncoder passwordEncoder) {
        this.service = userService;
        this.converter = userConverter;
        this.encoder = passwordEncoder;
    }

    /**
     * Find User by id.
     *
     * @param id the id of the User to find
     * @return a UserDTO if found
     */
    @ApiOperation(value = "Retrieve a User by its id from database")
    @GetMapping("/findById/{id}")
    public UserDTO findUserById(@PathVariable final Integer id) {
        LOGGER.debug("GET request sent from the UserApiController to"
                + " find a User by id {}", id);
        return service.findById(id);
    }

    /**
     * Retrieve all the Users from database.
     *
     * @return a list of all the Users
     */
    @ApiOperation(value = "Retrieve all the existing Users from database")
    @GetMapping("/findAll")
    public List<UserDTO> findAll() {
        LOGGER.debug("GET request sent from the UserApiController"
                + " to retrieve all the Users");
        return service.findAllUsers();
    }

    /**
     * a message that indicates a successful operation.
     * @param user the User that will be saved
     * @return a message that indicates a successful operation
     * @throws ResourceAlreadyExistsException if the username exists
     *                                        in database
     */
    @ApiOperation(value = "Save a new User to database")
    @PostMapping("/add")
    public String saveNewUser(@Valid @RequestBody final UserDTO user)
            throws ResourceAlreadyExistsException {
        LOGGER.debug("POST request sent from the"
                + " UserApiController to save a new User");
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User userToSave = converter.userDTOToUserEntityConverter(user);
        service.saveNewUser(userToSave);
        LOGGER.info("New User saved successfully {}", user.getId());
        return String.format("User: '%s' saved successfully",
                user.getFullName());
    }

    /**
     * Update an existing User.
     *
     * @param id   the id of the User to updated
     * @param user the new User information
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Update an existing User")
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable final Integer id,
                             @Valid @RequestBody final UserDTO user) {
        LOGGER.debug("PUT request sent from the"
                + " UserApiController to update User {}", id);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User userToUpdate = converter.userDTOToUserEntityConverter(user);
        service.updateUser(id, userToUpdate);
        LOGGER.info("User {} updated successfully", id);
        return String.format("User %s updated successfully", id);
    }

    /**
     * Delete an existing User.
     *
     * @param id the id of the User to delete
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Delete an existing User from database")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable final Integer id) {
        LOGGER.debug("DELETE request sent from the"
                + " UserApiController to delete User {}", id);
        service.deleteUser(id);
        LOGGER.info("User {} deleted successfully", id);
        return String.format("User %s delete successfully", id);
    }
}
