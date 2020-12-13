package com.nnk.poseidon.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.poseidon.converters.RuleNameConverter;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.services.RuleNameService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
@DisplayName("RuleNameApiController unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class RuleNameApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService service;

    @MockBean
    private RuleNameConverter converter;

    private RuleName ruleName;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        ruleName = dataLoader.setRuleName();
    }

    @DisplayName("GET: Find RuleName by id")
    @Test
    void givenValidRuleNameId_whenFindById_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ruleName/findById/1")
                .content(new ObjectMapper().writeValueAsString(ruleName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("GET: Retrieve all the RuleNames from database")
    @Test
    void findAllRuleNames_shouldReturnAListOfAllRuleNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ruleName/findAll")
                .content(new ObjectMapper().writeValueAsString(ruleName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Save RuleName successfully")
    @Test
    void givenValidRuleNameToSave_whenAddRuleName_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ruleName/add")
                .content(new ObjectMapper().writeValueAsString(ruleName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Save invalid RuleName returns Bad Request response")
    @Test
    void givenInvalidRuleNameToSave_whenAddRuleName_thenResponseShouldBeBadRequest() throws Exception {
        ruleName.setSqlPart("");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ruleName/add")
                .content(new ObjectMapper().writeValueAsString(ruleName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("PUT: Update RuleName successfully")
    @Test
    void givenValidRuleName_whenUpdateRuleName_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/ruleName/update/1")
                .content(new ObjectMapper().writeValueAsString(ruleName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("PUT: Updating RuleName returns Bad Request response")
    @Test
    void givenInvalidRuleName_whenUpdateRuleName_thenResponseShouldBeBadRequest() throws Exception {
        ruleName.setSqlPart("");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/ruleName/update/1")
                .content(new ObjectMapper().writeValueAsString(ruleName))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("DELETE: Delete RuleName successfully")
    @Test
    void givenValidRuleNameId_whenDeleteRuleName_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ruleName/delete/1"))
                .andExpect(status().isOk());
    }
}
