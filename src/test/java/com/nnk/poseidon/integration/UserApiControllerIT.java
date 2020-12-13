package com.nnk.poseidon.integration;

import com.nnk.poseidon.controllers.api.UserApiController;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.exceptions.ResourceAlreadyExistsException;
import com.nnk.poseidon.repositories.UserRepository;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("IntegrationTests")
@DisplayName("User api controller integration tests")
@AutoConfigureWebMvc
@SpringBootTest
@Sql(scripts = {"/sqlScriptsForITs/scriptForIT.sql"})
class UserApiControllerIT {

    @Autowired
    private UserApiController apiController;

    @Autowired
    private UserRepository repository;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        DataLoader dataLoader = new DataLoader();
        userDTO = dataLoader.setUserDTO();
    }

    @DisplayName("Retrieve all Users from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAll_shouldReturnAllUsers() {
        List<UserDTO> result = apiController.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(666, result.get(0).getId());
    }

    @DisplayName("FindUserById returns the correct User")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenCorrectUserId_whenFindById_thenCorrectUserShouldBeReturned() {
        Optional<UserDTO> expected = apiController.findUserById(666);
        assertTrue(expected.isPresent());
        assertEquals("db user name", expected.get().getUsername());
    }

    @DisplayName("FindUserById throws Exception when User id is incorrect")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenIncorrectUserId_whenFindById_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.findUserById(22));
    }

    @DisplayName("Save a new User successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidUser_whenSaveUser_thenUserShouldBeSaved() throws ResourceAlreadyExistsException {
        apiController.saveNewUser(userDTO);
        List<User> expected = repository.findAll();
        assertEquals(2, expected.size());
        assertEquals(userDTO.getUsername(), expected.get(1).getUsername());
    }

    @DisplayName("Save invalid User throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidUser_whenSaveUser_thenExceptionShouldBeThrown() {
        userDTO.setUsername("");
        assertThrows(Exception.class, () -> apiController.saveNewUser(userDTO));
    }

    @DisplayName("Save User with taken username throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidUserWithExistingUserName_whenSaveUser_thenExceptionShouldBeThrown() {
        userDTO.setUsername("db user name");
        assertThrows(ResourceAlreadyExistsException.class, () -> apiController.saveNewUser(userDTO));
    }

    @DisplayName("Update User successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidUser_whenUpdateUser_thenUserShouldBeUpdated() {
        userDTO.setUsername("new db user name");
        apiController.updateUser(666, userDTO);
        Optional<User> result = repository.findById(666);
        assertTrue(result.isPresent());
        assertEquals(userDTO.getUsername(), result.get().getUsername());
    }

    @DisplayName("Use invalid User to update an existing one throws exception successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidUser_whenUpdateUser_thenExceptionShouldBeThrown() {
        userDTO.setUsername("");
        assertThrows(Exception.class, () -> apiController.updateUser(666, userDTO));
    }

    @DisplayName("Delete an existing User successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidUserId_whenDeleteUser_thenUserShouldBeDeleted() {
        apiController.deleteUser(666);
        Optional<User> expected = repository.findById(666);
        assertFalse(expected.isPresent());
    }

    @DisplayName("Delete a User with wrong id throws an exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidUserId_whenDeleteUser_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.deleteUser(77));
    }
}
