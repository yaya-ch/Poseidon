package com.nnk.poseidon.unit.controllers.web;

import org.junit.jupiter.api.*;
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
@DisplayName("Home view controller unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class HomeControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    @DisplayName("Load the application home page")
    @Test
    void home_shouldReturnThePoseidonHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(view().name("home"))
                .andExpect(status().isOk());
    }

    @DisplayName("Redirect request to the login form when trying to access admin home page")
    @Test
    void givenNoCredentials_whenRequestingAdminHomePage_thenLoginFormShouldBeLoaded() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/home"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Redirect logged in admin to the bidList home page")
    @Test
    @WithMockUser(username = "admin", password = "test123", roles = {"ADMIN"})
    void givenCorrectAdminCredentials_whenRequestingAdminHomePage_thenResponseShouldBeRedirectionToBidListHomePage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/home"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Return an access denied when users with the USER role try to access admin home page")
    @Test
    @WithMockUser(username = "user", password = "test123")
    void givenCorrectUserCredentials_whenRequestingAdminHomePage_thenResponseShouldBe4XXClientError() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/home"))
                .andExpect(status().is4xxClientError());
    }
}