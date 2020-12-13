package com.nnk.poseidon.integration;

import com.nnk.poseidon.controllers.api.RuleNameApiController;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
import com.nnk.poseidon.repositories.RuleNameRepository;
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
@DisplayName("RuleName api controller integrsation tests")
@AutoConfigureWebMvc
@SpringBootTest
@Sql(scripts = {"/sqlScriptsForITs/scriptForIT.sql"})
class RuleNameApiControllerIT {

    @Autowired
    private RuleNameApiController apiController;

    @Autowired
    private RuleNameRepository repository;

    private RuleNameDTO ruleNameDTO;

    @BeforeEach
    void setUp() {
        DataLoader dataLoader = new DataLoader();
        ruleNameDTO = dataLoader.setRuleNameDTO();
    }

    @DisplayName("Retrieve all RuleNames from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAll_shouldReturnAllRuleNames() {
        List<RuleNameDTO> ruleNameDTOList = apiController.findAllRuleNames();
        assertNotNull(ruleNameDTOList);
        assertEquals(12, ruleNameDTOList.get(0).getId());
        assertEquals("Name", ruleNameDTOList.get(0).getName());
        assertEquals("Description", ruleNameDTOList.get(0).getDescription());
        assertEquals("json", ruleNameDTOList.get(0).getJson());
        assertEquals("template", ruleNameDTOList.get(0).getTemplate());
        assertEquals("sql str", ruleNameDTOList.get(0).getSqlStr());
        assertEquals("sql part", ruleNameDTOList.get(0).getSqlPart());
    }

    @DisplayName("Save a new RuleName successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRuleName_whenAddRuleName_thenRuleNameShouldBeSaved() {
        apiController.addRuleName(ruleNameDTO);
        List<RuleName> result = repository.findAll();
        assertEquals(2, result.size());
        assertEquals(ruleNameDTO.getDescription(), result.get(1).getDescription());
    }

    @DisplayName("Saving invalid RuleName throws an exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidRuleName_whenAddRuleName_thenExceptionShouldBeThrown() {
        ruleNameDTO.setDescription("");
        assertThrows(Exception.class, () -> apiController.addRuleName(ruleNameDTO));
    }

    @DisplayName("Updating RuleName successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRuleName_whenUpdateRuleName_thenRuleNameShouldBeUpdated() {
        apiController.updateRuleName(12, ruleNameDTO);
        Optional<RuleName> result = repository.findById(12);
        assertTrue(result.isPresent());
        assertEquals(result.get().getDescription(), ruleNameDTO.getDescription());
        assertEquals(result.get().getName(), ruleNameDTO.getName());
        assertEquals(result.get().getSqlPart(), ruleNameDTO.getSqlPart());
        assertEquals(result.get().getSqlStr(), ruleNameDTO.getSqlStr());
    }

    @DisplayName("Updating RuleName throws an exception when RuleName has errors")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidRuleName_whenUpdateRuleName_thenExceptionShouldBeThrown() {
        ruleNameDTO.setDescription("");
        assertThrows(Exception.class, () -> apiController.updateRuleName(12, ruleNameDTO));
    }

    @DisplayName("Delete RuleName successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRuleNameId_whenDeleteRuleName_thenRuleNameShouldBeDeleted() {
        apiController.deleteRuleName(12);
        Optional<RuleName> result = repository.findById(12);
        assertFalse(result.isPresent());
    }

    @DisplayName("Delete RuleName successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidRuleNameId_whenDeleteRuleName_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.deleteRuleName(123));
    }
}
