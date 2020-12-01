package com.nnk.poseidon.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.poseidon.converters.UserConverter;
import com.nnk.poseidon.domain.User;
import com.nnk.poseidon.services.UserService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yahia CHERIFI
 */

@Tag("ApiControllers")
@DisplayName("UserApiController unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class UserApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserConverter converter;

    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        user = dataLoader.setUser();
    }

    @DisplayName("GET: Retrieve User by id successfully")
    @Test
    void givenUserId_whenFindUserById_thenUserShouldBeReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/findById/1")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("GET: Retrieve all Users from database")
    @Test
    void findAll_shouldReturnAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/findAll")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: save valid User successfully")
    @Test
    void givenValidNewUser_whenSaveNewUser_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/add")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Saving invalid User throws exception")
    @Test
    void givenInvalidNewUser_whenSaveNewUser_thenExceptionShouldBeThrown() throws Exception {
        user.setUsername("");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/add")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("PUT: Update an existing User successfully")
    @Test
    void givenValidUser_whenUpdateUser_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update/1")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("PUT: Updating with invalid User throws exception")
    @Test
    void givenInvalidUser_whenUpdateUser_thenResponseShouldBeBadRequest() throws Exception {
        user.setUsername("");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/update/1")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("DELETE: delete an existing User successfully")
    @Test
    void givenUserId_whenDeleteUser_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/delete/1")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
