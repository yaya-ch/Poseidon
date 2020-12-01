package com.nnk.poseidon.unit.services;

import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.exceptions.ResourceAlreadyExistsException;
import com.nnk.poseidon.repositories.UserRepository;
import com.nnk.poseidon.services.UserService;
import com.nnk.poseidon.services.UserServiceImpl;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Services")
@DisplayName("UserService unit tests")
class UserServiceImplTest {

    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserConverter converter;

    private User user;
    private UserDTO userDTO;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UserServiceImpl(repository, converter);
        DataLoader dataLoader = new DataLoader();
        user = dataLoader.setUser();
        userDTO = dataLoader.setUserDTO();
    }

    @DisplayName("FindUserById returns correct User")
    @Test
    void givenCorrectUserId_whenFindById_thenUserShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(user));
        when(converter.userEntityToUserDTOConverter(any(User.class))).thenReturn(userDTO);
        UserDTO expected = service.findById(1);
        assertNotNull(expected);
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("FindUserById throws exception")
    @Test
    void givenIncorrectUserId_whenFindById_thenExceptionShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findById(1));
    }

    @DisplayName("Retrieve all users from database")
    @Test
    void findAllUsers_shouldReturnAListOfAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(repository.findAll()).thenReturn(userList);
        List<UserDTO> expectedList = service.findAllUsers();
        assertNotNull(expectedList);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("Save a new User successfully")
    @Test
    void givenNewUser_whenSaveNewUser_thenUserShouldBeSaved() throws ResourceAlreadyExistsException {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        service.saveNewUser(user);
        verify(repository, times(1)).save(user);
    }

    @DisplayName("Save new User with taken username throw exception")
    @Test
    void givenNewUserWithTakenUsername_whenSaveNewUser_thenExceptionShouldBeThrown() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.of(user));
        assertThrows(ResourceAlreadyExistsException.class, () -> service.saveNewUser(user));
        verify(repository, never()).save(user);
    }

    @DisplayName("Update a user successfully")
    @Test
    void givenCorrectUserId_whenUpdateUser_thenUserShouldBeUpdated() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(user));
        service.updateUser(1, user);
        verify(repository, times(1)).save(user);
    }

    @DisplayName("Update a User throws an exception")
    @Test
    void givenIncorrectUserId_whenUpdateUser_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.updateUser(1, user));
        verify(repository, never()).save(user);
    }

    @DisplayName("Delete a User successfully")
    @Test
    void givenCorrectUserId_whenDeleteUser_thenUserShouldBeDeleted() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(user));
        service.deleteUser(1);
        service.deleteUser(33);
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(1)).deleteById(33);
    }

    @DisplayName("Delete a user throw an exception")
    @Test
    void givenIncorrectUserId_whenDeleteUser_thenExceptionShouldBeThrown() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.deleteUser(1));
        verify(repository, never()).deleteById(1);
    }
}
