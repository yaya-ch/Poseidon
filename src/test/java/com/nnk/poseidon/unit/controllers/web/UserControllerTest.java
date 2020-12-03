package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.dto.UserDTO;
import com.nnk.poseidon.services.UserService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("ViewControllers")
@DisplayName("User view controller unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserConverter converter;



    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        user = dataLoader.setUser();
        userDTO = dataLoader.setUserDTO();
    }

    @DisplayName("Load the Users' home page")
    @Test
    void home_shouldReturnTheUserHomePage_andAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(view().name("user/list"))
                .andExpect(status().isOk());
        verify(service, times(1)).findAllUsers();
    }

    @DisplayName("Load the User addForm")
    @Test
    void addUser_shouldLoadTheAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/add"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/add"))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: save a valid User successfully and redirect to User home page")
    @Test
    void givenValidUser_whenSavingNewUser_thenResponseShouldBeRedirectionToUserHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("fullName", user.getFullName())
                .param("role", user.getRole()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/user/list"))
                .andReturn();
    }

    @DisplayName("POST: reload the addForm when trying to save an invalid User")
    @Test
    void givenInvalidUser_whenSavingNewUser_thenAddFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/validate")
                .param("username", "")
                .param("password", "")
                .param("fullName", user.getFullName())
                .param("role", user.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/add"))
                .andReturn();
    }

    @DisplayName("Load the updateForm successfully")
    @Test
    void givenCorrectUserId_whenShowUpdateForm_thenUpdateFormShouldBeReturned() throws Exception {
        when(service.findById(anyInt())).thenReturn(Optional.of(userDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/update?id=1"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"))
                .andExpect(status().isOk());
    }

    @DisplayName("Load 404 error page instead of updateForm when User id is incorrect")
    @Test
    void givenIncorrectUserId_whenShowUpdateForm_then404ErrorPageShouldBeLoaded() throws Exception {
        when(service.findById(anyInt())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/update?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("Save User successfully after update")
    @Test
    void givenValidUser_whenUpdateUser_thenResponseShouldBeRedirectionToUserHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("fullName", user.getFullName())
                .param("role", user.getRole()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/user/list"))
                .andReturn();
    }

    @DisplayName("Reload the updateForm when User has errors")
    @Test
    void givenInvalidUser_whenUpdateUser_thenUpdateFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1")
                .param("username", "")
                .param("password", user.getPassword())
                .param("fullName", user.getFullName())
                .param("role", user.getRole()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("user/update"))
                .andReturn();
    }

    @DisplayName("Delete an exiting User successfully")
    @Test
    void givenCorrectUserId_whenDeleteUser_thenResponseShouldBeRedirectionToUserHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/delete?id=1"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Load 404 error page when User id is incorrect")
    @Test
    void givenIncorrectUserId_whenDeleteUser_then404ErrorPageShouldBeReturned() throws Exception {
        when(service.findById(anyInt())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/delete?id=1"))
                .andExpect(view().name("404NotFound/404"));
    }
}
