package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.converters.RuleNameConverter;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
import com.nnk.poseidon.services.RuleNameService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("ViewControllers")
@DisplayName("RuleName view controller unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class RuleNameControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private RuleNameService service;

    @MockBean
    private RestTemplate template;

    @MockBean
    private RuleNameConverter converter;

    private RuleName ruleName;
    private RuleNameDTO ruleNameDTO;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        ruleName = dataLoader.setRuleName();
        ruleNameDTO = dataLoader.setRuleNameDTO();
    }

    @DisplayName("Load RuleName Home page")
    @Test
    void home_shouldReturnRuleNameHomePage_andAllRuleNames() throws Exception {
        List<RuleNameDTO> ruleNameDTOList = new ArrayList<>();
        ruleNameDTOList.add(ruleNameDTO);
        String findAllUrl = "http://localhost:8080/api/ruleName/findAll";
        when(template.exchange(
                findAllUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RuleNameDTO>>() { }
        )).thenReturn(new ResponseEntity<>(ruleNameDTOList, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleNameList"))
                .andExpect(view().name("ruleName/list"))
                .andExpect(status().isOk());
    }

    @DisplayName("Load RuleName add form")
    @Test
    void addRuleForm_shouldLoadTheAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/add"))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: save a valid RuleName successfully")
    @Test
    void givenValidRuleName_whenSavingNewRuleName_thenResponseShouldRedirectionToHomePage() throws Exception {
        when(converter.ruleNamDTOToRuleNameConverter(any(RuleNameDTO.class))).thenReturn(ruleName);
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .param("name", ruleName.getName())
                .param("description", ruleName.getDescription())
                .param("json", ruleName.getJson())
                .param("template", ruleName.getTemplate())
                .param("sqlStr", ruleName.getSqlStr())
                .param("sqlPart", ruleName.getSqlPart()))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andReturn();
    }

    @DisplayName("POST: save a invalid RuleName reloads the add form")
    @Test
    void givenInvalidRuleName_whenSavingNewRuleName_thenAddFormShouldBeReloaded() throws Exception {
        when(converter.ruleNamDTOToRuleNameConverter(any(RuleNameDTO.class))).thenReturn(ruleName);
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
                .param("name", "")
                .param("description", ruleName.getDescription())
                .param("json", ruleName.getJson())
                .param("template", ruleName.getTemplate())
                .param("sqlStr", ruleName.getSqlStr())
                .param("sqlPart", ruleName.getSqlPart()))
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(view().name("ruleName/add"))
                .andReturn();
    }

    @DisplayName("GET: Loading the RuleName update form successfully")
    @Test
    void showUpdateForm_shouldLoadTheRuleNameUpdateForm() throws Exception {
        when(service.findRuleNameById(anyInt())).thenReturn(Optional.of(ruleNameDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/update?id=1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("GET: Loading the RuleName update form load error page when id is incorrect")
    @Test
    void givenInvalidRuleNameId_whenUpdateRuleName_then404ErrorPageShouldBeLoaded() throws Exception {
        when(service.findRuleNameById(anyInt())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/update?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andReturn();
    }

    @DisplayName("PUT: Update RuleName successfully and redirect request to RuleName home page")
    @Test
    void givenValidRuleNameAndCorrectId_whenUpdateRuleName_thenResponseShouldBeRedirectionToRuleNameHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
                .contentType(MediaType.TEXT_HTML)
                .param("name", ruleName.getName())
                .param("description", ruleName.getDescription())
                .param("json", ruleName.getJson())
                .param("template", ruleName.getTemplate())
                .param("sqlStr", ruleName.getSqlStr())
                .param("sqlPart", ruleName.getSqlPart()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andReturn();
    }

    @DisplayName("PUT: saving invalid RuleName reloads the update form")
    @Test
    void givenInvalidRuleNameAndCorrectId_whenUpdateRuleName_thenUpdateFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/1")
                .contentType(MediaType.TEXT_HTML)
                .param("name", "")
                .param("description", ruleName.getDescription())
                .param("json", ruleName.getJson())
                .param("template", ruleName.getTemplate())
                .param("sqlStr", ruleName.getSqlStr())
                .param("sqlPart", ruleName.getSqlPart()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("ruleName/update"))
                .andReturn();
    }

    @DisplayName("DELETE: Delete a RuleName successfully and redirecting to the RuleName home page")
    @Test
    void givenValidRuleNameId_whenDeleteRuleName_thenResponseShouldBeRedirection() throws Exception {
        when(service.findRuleNameById(anyInt())).thenReturn(Optional.of(ruleNameDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete?id=1"))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection()).andReturn();
        verify(service, times(1)).findRuleNameById(1);
    }

    @DisplayName("DELETE: invalid RuleName id returns 404 error page")
    @Test
    void givenInvalidRuleNameId_whenDeleteRuleName_then404ErrorPageShouldBeReturned() throws Exception {
        when(service.findRuleNameById(anyInt())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andReturn();
    }
}