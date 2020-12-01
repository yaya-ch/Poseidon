package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;

import java.util.List;

/**
 * This interface provides some abstract methods that allow.
 * conversion from/to User entity/UserDTO
 *
 * @author Yahia CHERIFI
 */

public interface UserConverter {

    /**
     * Convert a UserDTO to User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return a User entity
     */
    User userDTOToUserEntityConverter(UserDTO userDTO);

    /**
     * Convert a list of UserDTOs to a list of User entities.
     *
     * @param userDTOList the list of UserDTO to convert
     * @return a list of User entities
     */
    List<User> userDTOListToUserEntityList(List<UserDTO> userDTOList);

    /**
     * Convert a User entity to UserDTO.
     *
     * @param user the User entity to convert
     * @return a UserDTO
     */
    UserDTO userEntityToUserDTOConverter(User user);

    /**
     * Convert a list of User entities to a list of UserDTOs.
     *
     * @param users the list of User entities to convert
     * @return a list of UserDTOs
     */
    List<UserDTO> userEntityListToUserDTOListConverter(List<User> users);
}
