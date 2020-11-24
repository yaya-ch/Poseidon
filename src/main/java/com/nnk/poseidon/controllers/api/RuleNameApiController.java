package com.nnk.poseidon.controllers.api;

import com.nnk.poseidon.converters.RuleNameConverter;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.RuleNameDTO;
import com.nnk.poseidon.services.RuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * This controller provides api endpoints.
 * that allow CRUD operations on RuleName
 *
 * @author Yahia CHERIFI
 */

@RestController
@RequestMapping("/api/ruleName")
public class RuleNameApiController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(RuleNameApiController.class);

    /**
     * RuleNameService to inject.
     */
    private final RuleNameService service;

    /**
     * RuleNameConverter to inject.
     */
    private final RuleNameConverter converter;

    /**
     * Instantiates a new RuleNameApiController.
     *
     * @param ruleNameService   the RuleNameService
     * @param ruleNameConverter the RuleNameConverter
     */
    @Autowired
    public RuleNameApiController(final RuleNameService ruleNameService,
                                 final RuleNameConverter ruleNameConverter) {
        this.service = ruleNameService;
        this.converter = ruleNameConverter;
    }

    /**
     * Find RuleName id.
     *
     * @param id RuleName id
     * @return the ruleName if found
     */
    @GetMapping("/findById/{id}")
    public Optional<RuleNameDTO> findById(@PathVariable final Integer id) {
        LOGGER.debug("GET request sent from the findById method of the"
                + " RuleNameApiController to retrieve RuleName {}", id);
        return service.findRuleNameById(id);
    }

    /**
     * Find all RuleNames.
     *
     * @return a list of RuleNameDTOs
     */
    @GetMapping("/findAll")
    public List<RuleNameDTO> findAllRuleNames() {
        LOGGER.debug("GET request sent from the findAllRuleNames method of the"
                + " RuleNameApiController to retrieve the RuleNames");
        return service.findAllRuleNames();
    }

    /**
     * Add a new RuleName.
     *
     * @param ruleName the RuleName to save
     * @return a message that indicates a successful operation
     */
    @PostMapping("/add")
    public String addRuleName(@Valid @RequestBody final RuleNameDTO ruleName) {
        LOGGER.debug("POST request sent from the addRuleName method of the"
                + " RuleNameApiController to save a new RuleName");
        RuleName ruleNameToSave = converter
                .ruleNamDTOToRuleNameConverter(ruleName);
        service.saveRuleName(ruleNameToSave);
        return "RuleName saved successfully";
    }

    /**
     * Update an existing RuleName.
     *
     * @param id          the id of the RuleName to update
     * @param ruleNameDTO the new RuleName information
     * @return a message that indicates a successful operation
     */
    @PutMapping("/update/{id}")
    public String updateRuleName(@PathVariable final Integer id,
                                 @Valid
                                 @RequestBody final RuleNameDTO ruleNameDTO) {
        LOGGER.debug("PUT request sent from the updateRuleName method of the"
                + " RuleNameApiController to update RuleName {}", id);
        service.updateRuleName(id, converter
                .ruleNamDTOToRuleNameConverter(ruleNameDTO));
        return String.format("RuleName %s Update successfully", id);
    }

    /**
     * Delete an existing RuleName.
     *
     * @param id the id of the RuleName to delete
     * @return a message that indicates a successful operation
     */
    @DeleteMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable final Integer id) {
        LOGGER.debug("DELETE request sent from the deleteRuleName method of"
                + " the RuleNameApiController to delete RuleName {}", id);
        service.deleteRuleName(id);
        return String.format("RuleName %s deleted successfully", id);
    }
}
