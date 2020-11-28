package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the UserConverter.
 *
 * @author Yahia CHERIF
 * @see UserConverter
 */

@Component
public class UserConverterImpl implements UserConverter {

    /**
     * ModelMapper to inject.
     */
    private final ModelMapper mapper;

    /**
     * Instantiates a new User converter.
     *
     * @param modelMapper the ModelMapper
     */
    @Autowired
    public UserConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * Convert a UserDTO to User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return a User entity
     */
    @Override
    public User userDTOToUserEntityConverter(final UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    /**
     * Convert a list of UserDTOs to a list of User entities.
     *
     * @param userDTOList the list of UserDTO to convert
     * @return a list of User entities
     */
    @Override
    public List<User> userDTOListToUserEntityList(
            final List<UserDTO> userDTOList) {
        return userDTOList.stream()
                .map(this::userDTOToUserEntityConverter)
                .collect(Collectors.toList());
    }

    /**
     * Convert a User entity to UserDTO.
     *
     * @param user the User entity to convert
     * @return a UserDTO
     */
    @Override
    public UserDTO userEntityToUserDTOConverter(final User user) {
        return mapper.map(user, UserDTO.class);
    }

    /**
     * Convert a list of User entities to a list of UserDTOs.
     *
     * @param users the list of User entities to convert
     * @return a list of UserDTOs
     */
    @Override
    public List<UserDTO> userEntityListToUserDTOListConverter(
            final List<User> users) {
        return users.stream()
                .map(this::userEntityToUserDTOConverter)
                .collect(Collectors.toList());
    }
}
