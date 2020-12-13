package com.nnk.poseidon.unit.converters;

import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.converters.UserConverterImpl;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Converters")
@DisplayName("UserConverter unit tests")
class UserConverterImplTest {

    private UserConverter converter;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        converter = new UserConverterImpl(new ModelMapper());
        DataLoader dataLoader = new DataLoader();
        user = dataLoader.setUser();
        userDTO = dataLoader.setUserDTO();
    }

    @DisplayName("Convert a UserDTO into a User entity")
    @Test
    void givenUserDTO_whenUserDTOToUserEntityConverterIsCalled_thenUserEntityShouldBeReturned() {
        User expected = converter.userDTOToUserEntityConverter(userDTO);

        assertNotNull(expected);
        assertEquals(expected.getId(), userDTO.getId());
        assertEquals(expected.getUsername(), userDTO.getUsername());
        assertEquals(expected.getPassword(), userDTO.getPassword());
        assertEquals(expected.getFullName(), userDTO.getFullName());
        assertEquals(expected.getRole(), userDTO.getRole());
    }

    @DisplayName("Convert a list of UserDTOs to a list of User entities")
    @Test
    void givenUserDTOList_whenUserDTOListToUserEntityListIsCalled_thenUserEntityListShouldBeReturned() {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);

        List<User> expected = converter.userDTOListToUserEntityList(userDTOList);

        assertNotNull(expected);
        assertEquals(expected.size(), userDTOList.size());
        assertEquals(expected.get(0).getRole(), userDTOList.get(0).getRole());
    }

    @DisplayName("Convert a User entity to UserDTO")
    @Test
    void givenUserEntity_whenUserEntityToUserDTOConverter_thenUserDTOShouldBeReturned() {
        UserDTO expected = converter.userEntityToUserDTOConverter(user);

        assertNotNull(expected);
        assertEquals(expected.getId(), user.getId());
        assertEquals(expected.getUsername(), user.getUsername());
        assertEquals(expected.getFullName(), user.getFullName());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected.getRole(), user.getRole());
    }

    @DisplayName("Convert a list of User entities to a list of UserDTOs")
    @Test
    void givenUserEntityList_whenUserEntityListToUserDTOListConverterIsCalled_thenUserDTOListShouldBeReturned() {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserDTO> expected = converter.userEntityListToUserDTOListConverter(userList);

        assertNotNull(expected);
        assertEquals(expected.size(), userList.size());
        assertEquals(expected.get(0).getRole(), userList.get(0).getRole());
    }
}
