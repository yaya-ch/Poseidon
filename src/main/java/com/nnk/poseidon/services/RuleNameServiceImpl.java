package com.nnk.poseidon.services;

import com.nnk.poseidon.converters.RuleNameConverter;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
import com.nnk.poseidon.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the RuleName interface.
 *
 * @author Yahia CHERIFI
 * @see RuleNameService
 */

@Service
public class RuleNameServiceImpl implements RuleNameService {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(RuleNameServiceImpl.class);

    /**
     * RuleNameRepository to inject.
     */
    private final RuleNameRepository repository;

    /**
     * RuleNameConverter to inject.
     */
    private final RuleNameConverter converter;

    /**
     * Instantiates a new Rule name service.
     *
     * @param ruleNameRepository the RuleNameRepository
     * @param ruleNameConverter  the RuleNameConverter
     */
    public RuleNameServiceImpl(final RuleNameRepository ruleNameRepository,
                               final RuleNameConverter ruleNameConverter) {
        this.repository = ruleNameRepository;
        this.converter = ruleNameConverter;
    }

    /**
     * Find RuleName by id.
     *
     * @param id the RuleName id
     * @return the RuleName if found
     */
    @Override
    public Optional<RuleNameDTO> findRuleNameById(final Integer id) {
        Optional<RuleName> findById = repository.findById(id);
        if (findById.isPresent()) {
            LOGGER.info("RuleName {} loaded successfully", id);
            return Optional.ofNullable(converter
                    .ruleNameEntityToRuleNameDTOConverter(findById.get()));
        } else {
            LOGGER.error("Failed to load RuleName {}."
                    + " No matching resource found", id);
            throw new NoSuchElementException(
                    String.format("No resource found for id %s", id));
        }
    }

    /**
     * Find all RuleNames.
     *
     * @return a list of all the RuleNames
     */
    @Override
    public List<RuleNameDTO> findAllRuleNames() {
        List<RuleName> result = repository.findAll();
        return converter.ruleNameEntityListToRuleNameDTOListConverter(result);
    }

    /**
     * Save a new RuleName.
     *
     * @param ruleName the RuleName to save
     * @return a call to the repo layer
     */
    @Override
    public RuleName saveRuleName(final RuleName ruleName) {
        return repository.save(ruleName);
    }

    /**
     * Update an existing RuleName.
     *
     * @param id       the id of the RuleName to update
     * @param ruleName the new RuleName information
     * @return a call to the repo layer
     */
    @Override
    public RuleName updateRuleName(final Integer id, final RuleName ruleName) {
        Optional<RuleName> findRuleNameById = repository.findById(id);
        if (findRuleNameById.isPresent()) {
            LOGGER.info("RuleName {} updated successfully", id);
            ruleName.setId(findRuleNameById.get().getId());
            return repository.save(ruleName);
        } else {
            LOGGER.error("Failed to load RuleName {}. No resources found", id);
            throw new NoSuchElementException(String.format(
                    "Failed to update RuleName. No resource found for id %s",
                    id));
        }
    }

    /**
     * Delete an existing RuleName.
     *
     * @param id the id of the RuleName to delete
     */
    @Override
    public void deleteRuleName(final Integer id) {
        Optional<RuleName> findRuleNameById = repository.findById(id);
        if (findRuleNameById.isPresent()) {
            repository.deleteById(id);
            LOGGER.info("RuleName {} deleted successfully", id);
        } else {
            LOGGER.error("Failed to delete RuleName {}."
                    + " No resource found for the provided id", id);
            throw new NoSuchElementException(String.format(
                    "Failed to Delete RuleName. No resource found for id %s",
                    id));
        }
    }
}
