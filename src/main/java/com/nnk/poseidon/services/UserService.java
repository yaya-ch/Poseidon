package com.nnk.poseidon.services;

import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.exceptions.ResourceAlreadyExistsException;

import java.util.List;

/**
 * The interface TradeService.
 * It contains abstract methods that provide
 * the logic to operated on the data sent to and from
 * the User controllers and the repository layer
 *
 * @author Yahia CHERIFI
 */

public interface UserService {

    /**
     * Find User by id.
     *
     * @param id the id of the User
     * @return the User if found
     */
    UserDTO findById(Integer id);

    /**
     * Find all the Users.
     *
     * @return a list of all the Users
     */
    List<UserDTO> findAllUsers();

    /**
     * Save a new User.
     *
     * @param user the User to save
     * @return a call to the repo layer
     * @throws ResourceAlreadyExistsException if the username exists
     */
    User saveNewUser(User user) throws ResourceAlreadyExistsException;

    /**
     * Update an existing User.
     *
     * @param id   the id of the User to update
     * @param user the new User information
     * @return a call to the repo layer
     */
    User updateUser(Integer id, User user);

    /**
     * Delete an existing user.
     *
     * @param id the id of the User to delete
     */
    void deleteUser(Integer id);

}
