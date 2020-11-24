package com.nnk.poseidon.unit.converters;

import com.nnk.poseidon.converters.RuleNameConverter;
import com.nnk.poseidon.converters.RuleNameConverterImpl;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
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
@DisplayName("RuleNameConverter unit tests")
class RuleNameConverterImplTest {

    private RuleNameConverter converter;

    private RuleName ruleName;

    private RuleNameDTO ruleNameDTO;

    @BeforeEach
    void setUp() {
        ModelMapper mapper = new ModelMapper();
        converter = new RuleNameConverterImpl(mapper);
        DataLoader dataLoader = new DataLoader();
        ruleName = dataLoader.setRuleName();
        ruleNameDTO = dataLoader.setRuleNameDTO();
    }

    @DisplayName("Convert RuleNameDTO to RuleName entity")
    @Test
    void givenRuleNameDTO_whenConverted_theRuleNameEntityShouldBeReturned() {
        RuleName result = converter.ruleNamDTOToRuleNameConverter(ruleNameDTO);
        assertNotNull(result);
        assertEquals(result.getId(), ruleNameDTO.getId());
        assertEquals(result.getName(), ruleNameDTO.getName());
        assertEquals(result.getDescription(), ruleNameDTO.getDescription());
        assertEquals(result.getJson(), ruleNameDTO.getJson());
        assertEquals(result.getTemplate(), ruleNameDTO.getTemplate());
        assertEquals(result.getSqlStr(), ruleNameDTO.getSqlStr());
        assertEquals(result.getSqlPart(), ruleNameDTO.getSqlPart());
    }

    @DisplayName("Convert a list of RuleNameDTOs to a list of RuleName entities")
    @Test
    void givenRuleNameDTOList_whenConverted_thenRuleNameEntityListShouldBeReturned() {
        List<RuleNameDTO> ruleNameDTOList = new ArrayList<>();
        ruleNameDTOList.add(ruleNameDTO);
        List<RuleName> result = converter.ruleNameDTOListToRuleNameEntityConverter(ruleNameDTOList);

        assertEquals(result.size(), ruleNameDTOList.size());
        assertEquals(result.get(0).getId(), ruleNameDTOList.get(0).getId());
    }

    @DisplayName("Convert RuleName entity to RuleNameDTO")
    @Test
    void givenRuleNameEntity_whenConverted_thenRuleNameDTOShouldBeReturned() {
        RuleNameDTO result = converter.ruleNameEntityToRuleNameDTOConverter(ruleName);
        assertNotNull(result);
        assertEquals(result.getId(), ruleName.getId());
        assertEquals(result.getName(), ruleName.getName());
        assertEquals(result.getDescription(), ruleName.getDescription());
        assertEquals(result.getJson(), ruleName.getJson());
        assertEquals(result.getTemplate(), ruleName.getTemplate());
        assertEquals(result.getSqlStr(), ruleName.getSqlStr());
        assertEquals(result.getSqlPart(), ruleName.getSqlPart());
    }

    @DisplayName("Convert RuleName entity list to a list of RuleNameDTO list")
    @Test
    void givenRuleNameEntityList_whenConverted_thenRuleNameDTOListShouldBeReturned() {
        List<RuleName> ruleNames = new ArrayList<>();
        ruleNames.add(ruleName);
        List<RuleNameDTO> result = converter.ruleNameEntityListToRuleNameDTOListConverter(ruleNames);
        assertEquals(result.size(), ruleNames.size());
        assertEquals(result.get(0).getDescription(), ruleNames.get(0).getDescription());
    }
}