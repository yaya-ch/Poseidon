package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;

import java.util.List;

/**
 * This interface provides some abstract methods that allow.
 * conversion from/to RuleName entity/RatingDTO
 *
 * @author Yahia CHERIFI
 */

public interface RuleNameConverter {

    /**
     * Converts a RuleNameDTO to a a RUleName entity.
     *
     * @param ruleNameDTO the RuleNameDTO that will be converted
     * @return a RuleName entity
     */
    RuleName ruleNamDTOToRuleNameConverter(RuleNameDTO ruleNameDTO);

    /**
     * Converts a list of RuleNameDTOs to a list of RuleName entities.
     *
     * @param ruleNameDTOList the list of RuleNameDTOs that will be converted
     * @return a list of RuleName entities
     */
    List<RuleName> ruleNameDTOListToRuleNameEntityConverter(
            List<RuleNameDTO> ruleNameDTOList);

    /**
     * Converts a RuleName entity to a RuleNameDTOs.
     *
     * @param ruleName the RuleName that will be converted
     * @return a RuleNameDTO
     */
    RuleNameDTO ruleNameEntityToRuleNameDTOConverter(RuleName ruleName);

    /**
     * Converts a list of RuleName entities to a list of RuleNameDTOs.
     *
     * @param ruleNameList the RuleName list that will be converted
     * @return a list of RuleNameDTOs
     */
    List<RuleNameDTO> ruleNameEntityListToRuleNameDTOListConverter(
            List<RuleName> ruleNameList);
}
