package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.exceptions.ResourceAlreadyExistsException;
import com.nnk.poseidon.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type UserController controller.
 * This controller provide web services that allow CRUD operations on User
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(UserController.class);

    /**
     * Static String attribute for users.
     */
    private static final String USER_LIST = "users";

    /**
     * Static String attribute for the redirection link.
     * redirect to Users' home page
     */
    private static final String REDIRECTION_LINK = "redirect:/user/list";

    /**
     * UserService to inject.
     */
    private final UserService service;

    /**
     * UserConverter to inject.
     */
    private final UserConverter converter;

    /**
     * PasswordEncoder to inject.
     */
    private final PasswordEncoder encoder;

    /**
     * Instantiates a new UserController.
     *
     * @param userService     the UserService
     * @param userConverter   the UserConverter
     * @param passwordEncoder the PasswordEncoder
     */
    public UserController(final UserService userService,
                          final UserConverter userConverter,
                          final PasswordEncoder passwordEncoder) {
        this.service = userService;
        this.converter = userConverter;
        this.encoder = passwordEncoder;
    }

    /**
     * The User home page that displays all the Users.
     *
     * @param model the model
     * @return an html page that displays all the Users
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from the UserController to load the"
                + " User home page");
        model.addAttribute(USER_LIST, service.findAllUsers());
        return "user/list";
    }

    /**
     * Load the html addForm that allows adding new Users.
     *
     * @param model the model
     * @return an html form that allows adding new Users
     */
    @GetMapping("/add")
    public String addUser(final Model model) {
        LOGGER.debug("GET request sent from the UserController"
                + " to load the addForm");
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "user/add";
    }

    /**
     * Validate the user input and save the new User to database.
     *
     * @param user   the User to save
     * @param result the result
     * @param model  the model
     * @return the addForm if User contains errors
     * or redirect the user to the User home page
     * @throws ResourceAlreadyExistsException thrown when the provided
     *                                      username already exists in database
     */
    @PostMapping("/validate")
    public String validate(@Valid final UserDTO user,
                           final BindingResult result,
                           final Model model)
            throws ResourceAlreadyExistsException {
        LOGGER.debug("POST request sent from the UserController"
                + " to save new User");
        if (!result.hasErrors()) {
            user.setPassword(encoder.encode(user.getPassword()));
            User userToSave = converter.userDTOToUserEntityConverter(user);
            service.saveNewUser(userToSave);
            LOGGER.info("User saved successfully by the UserController");
            model.addAttribute(USER_LIST, service.findAllUsers());
            return REDIRECTION_LINK;
        } else {
            LOGGER.error("Failed to save the User because of invalid input."
                    + " AddForm reloaded");
            model.addAttribute("user", user);
            return "user/add";
        }
    }

    /**
     * Load an html form that allows updating existing Users.
     *
     * @param id    the id of the User to updated
     * @param model the model
     * @return the html form if the id is correct
     * or a 404 error page if the id is incorrect
     */
    @GetMapping("/update")
    public String showUpdateForm(
            @RequestParam final Integer id,
            final Model model) {
        LOGGER.debug("GET request sent from the UserController to load the"
                + " updateForm and update User {}", id);
        try {
            Optional<UserDTO> userDTO = service.findById(id);
            if (userDTO.isPresent()) {
                LOGGER.info("updateForm loaded successfully");
                model.addAttribute("user", userDTO.get());
            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Failed to load User {}."
                    + " No matching resource is present", id);
            return "404NotFound/404";
        }
        return "user/update";
    }

    /**
     * Save a User after update.
     *
     * @param id     the id of the User to update
     * @param user   the new User information
     * @param result the result
     * @param model  the model
     * @return the User home page if the updated User has no errors
     * or the updateForm if the new User information contains errors
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") final Integer id,
                             @Valid final UserDTO user,
                             final BindingResult result,
                             final Model model) {
        LOGGER.debug("POST request sent from the UserController"
                + " to update User {}", id);
        if (!result.hasErrors()) {
            user.setPassword(encoder.encode(user.getPassword()));
            User userToUpdate = converter.userDTOToUserEntityConverter(user);
            service.updateUser(id, userToUpdate);
            LOGGER.info("User {} updated successfully", id);
            model.addAttribute(USER_LIST, service.findAllUsers());
            return REDIRECTION_LINK;
        }
        LOGGER.error("Failed to update User {}. UpdateForm reloaded", id);
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Delete an existing User.
     *
     * @param id    the id of the User to delete
     * @return the User home page if the id is correct
     * or a 404 error page if the id is incorrect
     */
    @GetMapping("/delete")
    public String deleteUser(@RequestParam final Integer id) {
        LOGGER.debug("GET request sent from the UserController"
                + " to delete User {}", id);
        try {
            Optional<UserDTO> findUser = service.findById(id);
            if (findUser.isPresent()) {
                service.deleteUser(id);
                LOGGER.info("User {} deleted successfully", id);
            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Deletion failed. No matching User resource"
                    + " for id: {}", id);
            return "404NotFound/404";
        }
        return REDIRECTION_LINK;
    }
}
