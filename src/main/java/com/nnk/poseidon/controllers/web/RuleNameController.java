package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.constants.ApiUrlConstants;
import com.nnk.poseidon.dto.RuleNameDTO;
import com.nnk.poseidon.services.RuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type RuleName controller.
 * This controller provide web services that allow CRUD operations on RuleName
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/ruleName")
public class RuleNameController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(RuleNameController.class);

    /**
     * Static string for the RuleName attribute.
     */
    private static final String RULE_NAME_ATTRIBUTE = "ruleName";

    /**
     * Static string for the redirection link.
     */
    private static final String REDIRECTION_LINK = "redirect:/ruleName/list";

    /**
     * RuleNameService to inject.
     */
    private final RuleNameService service;

    /**
     * RestTemplate to inject.
     */
    private final RestTemplate template;

    /**
     * Instantiates a new RuleNameController.
     *  @param ruleNameService   the RuleNameService
     * @param restTemplate RestTemplate instance that is used for
     *                     consuming the API
     */
    @Autowired
    public RuleNameController(final RuleNameService ruleNameService,
                              final RestTemplate restTemplate) {
        this.service = ruleNameService;
        this.template = restTemplate;
    }

    /**
     * The RuleName home page that displays all the RuleNames.
     *
     * @param model the model
     * @return an html page that displays all the RuleNames
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from the"
                + " RuleNameController to load all the RuleNames");
        String findAllRuleNamesUrl = ApiUrlConstants.RULE_NAME_API_BASE_URL
                + "/findAll";
        ResponseEntity<List<RuleNameDTO>> responseEntity = template.exchange(
                findAllRuleNamesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RuleNameDTO>>() { }
        );
        model.addAttribute("ruleNameList", responseEntity.getBody());
        return "ruleName/list";
    }

    /**
     * Load the addForm that allows adding a new RuleName.
     *
     * @param model the model
     * @return the html template that displays the form
     */
    @GetMapping("/add")
    public String addRuleForm(final Model model) {
        LOGGER.debug("GET request sent from the"
                + " RuleNameController to load the add form");
        RuleNameDTO ruleName = new RuleNameDTO();
        model.addAttribute(RULE_NAME_ATTRIBUTE, ruleName);
        return "ruleName/add";
    }

    /**
     * Save a new RuleName to the database.
     *
     * @param ruleNameDTO the RuleName to save.
     * @param result      the result
     * @param model       the model
     * @return the addRuleNameForm if RuleName contains errors
     * or redirect the user to the RuleName home page
     */
    @PostMapping("/validate")
    public String validate(@Valid
                           @ModelAttribute("ruleName")
                           final RuleNameDTO ruleNameDTO,
                           final BindingResult result,
                           final Model model) {
        LOGGER.debug("POST request sent from the"
                + " RuleNameController to save a new RuleName");
        String addRuleName = ApiUrlConstants.RULE_NAME_API_BASE_URL + "/add";
        if (!result.hasErrors()) {
            HttpEntity<RuleNameDTO> httpEntity = new HttpEntity<>(ruleNameDTO);
            template.exchange(
                    addRuleName,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            LOGGER.info("New RuleName saved successfully"
                    + " by the RuleNameController");
            return REDIRECTION_LINK;
        }
        LOGGER.error("Failed to save the new RuleName. Add form reloaded");
        model.addAttribute(RULE_NAME_ATTRIBUTE, ruleNameDTO);
        return "ruleName/add";
    }

    /**
     * Load the update form that allows updating an existing RuleName.
     *
     * @param id    the id of the RuleName to update
     * @param model the model
     * @return the form if id is correct or an error page if the id is incorrect
     */
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam final Integer id,
                                 final Model model) {
        LOGGER.debug("GET request sent from"
                + " RuleNameController to load the RuleName Update form");
        try {
            String findRuleNameById = ApiUrlConstants.RULE_NAME_API_BASE_URL
                    + "/findById/" + id;
            ResponseEntity<RuleNameDTO> responseEntity = template.exchange(
                    findRuleNameById,
                    HttpMethod.GET,
                    null,
                    RuleNameDTO.class
            );
            if (responseEntity.hasBody()) {
                LOGGER.info("RuleName {} Loaded successfully", id);
                model.addAttribute(RULE_NAME_ATTRIBUTE,
                        responseEntity.getBody());
            }
        } catch (HttpServerErrorException e) {
            LOGGER.error("Failed to load RuleName {}."
                    + " No matching resource is present", id);
            return "404NotFound/404";
        }
        return "ruleName/update";
    }

    /**
     * Save a RuleName after update.
     *
     * @param id          the id of the RuleName to update
     * @param ruleNameDTO the new RuleName information
     * @param result      the result
     * @param model       the model
     * @return the update form if RuleName contains errors
     * or redirect the user to the RuleName home page
     */
    @PostMapping("/update/{id}")
    public String updateRuleName(@PathVariable("id") final Integer id,
                                 @Valid
                                 @ModelAttribute("ruleName")
                                 final RuleNameDTO ruleNameDTO,
                                 final BindingResult result,
                                 final Model model) {
        LOGGER.debug("POST request sent from the"
                + " RuleNameController to update RuleName {}", id);
        String updateRuleName = ApiUrlConstants.RULE_NAME_API_BASE_URL
                + "/update/" + id;
        if (!result.hasErrors()) {
            HttpEntity<RuleNameDTO> httpEntity = new HttpEntity<>(ruleNameDTO);
            template.exchange(
                    updateRuleName,
                    HttpMethod.PUT,
                    httpEntity,
                    String.class
            );
            LOGGER.info("RuleName {} updated successfully", id);
            return  REDIRECTION_LINK;
        }
        LOGGER.error("Failed to save RuleName {}. Update form reloaded", id);
        model.addAttribute(RULE_NAME_ATTRIBUTE, ruleNameDTO);
        return "ruleName/update";
    }

    /**
     * Delete an existing RuleName.
     *
     * @param id the id of the RuleName to delete
     * @return en error page if the id is incorrect
     * or redirect to the RuleName home page
     */
    @GetMapping("/delete")
    public String deleteRuleName(@RequestParam("id") final Integer id) {
        LOGGER.debug("GET request sent from"
                + " RuleNameController to delete RuleName {}", id);
        try {
            Optional<RuleNameDTO> find = service.findRuleNameById(id);
            if (find.isPresent()) {
                service.deleteRuleName(id);
                LOGGER.info("RuleName {} deleted successfully", id);
            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Failed to delete RuleName {}."
                    + " No Resource found for the provided id.", id);
            return "404NotFound/404";
        }
        return REDIRECTION_LINK;
    }
}
