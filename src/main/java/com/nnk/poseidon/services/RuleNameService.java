package com.nnk.poseidon.services;

import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;

import java.util.List;
import java.util.Optional;

/**
 * The interface RuleNameService.
 * It contains abstract methods that provide
 * the logic to operated on the data sent to and from
 * the RuleName controllers and the repository layer
 *
 * @author Yahia CHERIFI
 */

public interface RuleNameService {

    /**
     * Find RuleName by id.
     *
     * @param id the RuleName id
     * @return the RuleName if found
     */
    Optional<RuleNameDTO> findRuleNameById(Integer id);

    /**
     * Find all RuleNames.
     *
     * @return a list of all the RuleNames
     */
    List<RuleNameDTO> findAllRuleNames();

    /**
     * Save a new RuleName.
     *
     * @param ruleName the RuleName to save
     * @return a call to the repo layer
     */
    RuleName saveRuleName(RuleName ruleName);

    /**
     * Update an existing RuleName.
     *
     * @param id       the id of the RuleName to update
     * @param ruleName the new RuleName information
     * @return a call to the repo layer
     */
    RuleName updateRuleName(Integer id, RuleName ruleName);

    /**
     * Delete an existing RuleName.
     *
     * @param id the id of the RuleName to delete
     */
    void deleteRuleName(Integer id);
}
