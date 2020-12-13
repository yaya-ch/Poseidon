package com.nnk.poseidon.unit.controllers.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("ViewControllers")
@DisplayName("Login view controller unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    @DisplayName("Load the login form successfully")
    @Test
    void login_shouldReturnTheLoginForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }

    @DisplayName("Admin logs to app/secure/article-details successfully")
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    void givenCorrectAdminCredentials_whenGetAllUserArticles_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/secure/article-details"))
                .andExpect(view().name("user/list"))
                .andExpect(status().isOk());
    }

    @DisplayName("User with ROLE_USER cannot log to app/secure/article-details")
    @WithMockUser(username = "user", password = "user")
    @Test
    void givenCorrectUserCredentials_whenGetAllUserArticles_thenResponseShouldBe4xx() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/secure/article-details"))
                .andExpect(status().is4xxClientError());
    }

    @DisplayName("Redirecting unlogged users to login form when trying to access app/secure/article-details")
    @Test
    void givenNoCredentials_whenGetAllUserArticles_thenResponseShouldBeRedirectionToLogInForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/secure/article-details"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }
}