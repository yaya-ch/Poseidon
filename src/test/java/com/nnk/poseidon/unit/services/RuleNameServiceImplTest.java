package com.nnk.poseidon.unit.services;

import com.nnk.poseidon.converters.RuleNameConverter;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
import com.nnk.poseidon.repositories.RuleNameRepository;
import com.nnk.poseidon.services.RuleNameServiceImpl;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Services")
@DisplayName("RuleName unit tests")
class RuleNameServiceImplTest {

    private RuleNameServiceImpl service;

    @Mock
    private RuleNameRepository repository;

    @Mock
    private RuleNameConverter converter;

    private RuleName ruleName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RuleNameServiceImpl(repository, converter);
        DataLoader dataLoader = new DataLoader();
        ruleName = dataLoader.setRuleName();
    }

    @DisplayName("FindRuleNameById returns correct RuleName")
    @Test
    void givenValidRuleNameId_whenFindRuleNameById_thenCorrectRuleNameShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        Optional<RuleNameDTO> expected = service.findRuleNameById(1);
        assertNotNull(expected);
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("FindRuleNameById throws an exception")
    @Test
    void givenInvalidRuleNameId_whenFindRuleNameById_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findRuleNameById(1));
    }

    @DisplayName("Find all RuleNames returns a list of RuleNames")
    @Test
    void findAllRuleNames_shouldReturnAListOfAllRuleNames() {
        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(ruleName);
        when(repository.findAll()).thenReturn(ruleNameList);
        List<RuleNameDTO> expected = service.findAllRuleNames();
        assertNotNull(expected);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("Save RuleName successfully")
    @Test
    void givenRuleName_whenSaveRuleName_thenRuleNameShouldBeSaved() {
        when(repository.save(any(RuleName.class))).thenReturn(ruleName);
        service.saveRuleName(ruleName);
        verify(repository, times(1)).save(ruleName);
    }

    @DisplayName("Update RuleName successfully")
    @Test
    void givenRuleName_whenUpdateRuleName_thenRuleNameShouldBeSaved() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        service.updateRuleName(1, ruleName);
        verify(repository, times(1)).save(ruleName);
    }

    @DisplayName("Update RuleName throws an exception")
    @Test
    void givenInvalidRuleNameId_whenUpdateRuleName_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.updateRuleName(1, ruleName));
        verify(repository, never()).save(ruleName);
    }

    @DisplayName("Delete RuleName successfully")
    @Test
    void givenValidRuleNameId_whenDeleteRuleName_thenRuleNameShouldBeDeleted() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        service.deleteRuleName(1);
        service.deleteRuleName(2);
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(1)).deleteById(2);
    }

    @DisplayName("Delete RuleName throw an exception")
    @Test
    void givenInvalidRuleNameId_whenDeleteRuleName_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.deleteRuleName(1));
        verify(repository, never()).deleteById(1);
    }
}