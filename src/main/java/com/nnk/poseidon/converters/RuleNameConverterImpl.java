package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the RuleNameConverter.
 *
 * @author Yahia CHERIF
 * @see RuleNameConverter
 */
@Component
public class RuleNameConverterImpl implements RuleNameConverter {

    /**
     * Model mapper to inject.
     */
    private final ModelMapper mapper;

    /**
     * Instantiates a new Rule name converter.
     *
     * @param modelMapper the model mapper
     */
    @Autowired
    public RuleNameConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * Converts a RuleNameDTO to a a RUleName entity.
     *
     * @param ruleNameDTO the RuleNameDTO that will be converted
     * @return a RuleName entity
     */
    @Override
    public RuleName ruleNamDTOToRuleNameConverter(
            final RuleNameDTO ruleNameDTO) {
        return mapper.map(ruleNameDTO, RuleName.class);
    }

    /**
     * Converts a list of RuleNameDTOs to a list of RuleName entities.
     *
     * @param ruleNameDTOList the list of RuleNameDTOs that will be converted
     * @return a list of RuleName entities
     */
    @Override
    public List<RuleName> ruleNameDTOListToRuleNameEntityConverter(
            final List<RuleNameDTO> ruleNameDTOList) {
        return ruleNameDTOList.stream()
                .map(this::ruleNamDTOToRuleNameConverter)
                .collect(Collectors.toList());
    }

    /**
     * Converts a RuleName entity to a RuleNameDTOs.
     *
     * @param ruleName the RuleName that will be converted
     * @return a RuleNameDTO
     */
    @Override
    public RuleNameDTO ruleNameEntityToRuleNameDTOConverter(
            final RuleName ruleName) {
        return mapper.map(ruleName, RuleNameDTO.class);
    }

    /**
     * Converts a list of RuleName entities to a list of RuleNameDTOs.
     *
     * @param ruleNameList the RuleName list that will be converted
     * @return a list of RuleNameDTOs
     */
    @Override
    public List<RuleNameDTO> ruleNameEntityListToRuleNameDTOListConverter(
            final List<RuleName> ruleNameList) {
        return ruleNameList.stream()
                .map(this::ruleNameEntityToRuleNameDTOConverter)
                .collect(Collectors.toList());
    }
}
