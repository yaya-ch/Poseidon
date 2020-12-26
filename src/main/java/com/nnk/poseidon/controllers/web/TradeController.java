package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.constants.ApiUrlConstants;
import com.nnk.poseidon.dto.TradeDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * The type RuleName controller.
 * This controller provide web services that allow CRUD operations on Trade
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/trade")
public class TradeController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TradeController.class);

    /**
     * Static string for the Trade attribute.
     */
    private static final String TRADE_ATTRIBUTE = "trade";

    /**
     * Static string for the Trade list attribute.
     */
    private static final String TRADE_LIST_ATTRIBUTE = "tradeList";

    /**
     * Static string for the redirection link.
     */
    private static final String REDIRECTION_LINK = "redirect:/trade/list";

    /**
     * RestTemplate to inject.
     */
    private final RestTemplate template;

    /**
     * Instantiates a new Trade controller.
     * @param restTemplate RestTemplate instance that is used for
     *                     consuming the API
     */
    @Autowired
    public TradeController(final RestTemplate restTemplate) {
        this.template = restTemplate;
    }

    /**
     * The Trade home page that displays all the Trades.
     *
     * @param model the model
     * @return an html page that displays all the Trades
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from the TradeController to load the"
                + " Trade home page");
        String findAllUrl = ApiUrlConstants.TRADE_API_BASE_URL + "/findAll";
        ResponseEntity<List<TradeDTO>> responseEntity = template.exchange(
                findAllUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TradeDTO>>() { }
        );
        model.addAttribute(TRADE_LIST_ATTRIBUTE, responseEntity.getBody());
        return "trade/list";
    }

    /**
     * Load the addForm that allows adding a new Trade.
     *
     * @param model the model
     * @return an html form that allows adding new Trades
     */
    @GetMapping("/add")
    public String addTrade(final Model model) {
        LOGGER.debug("GET request sent from the TradeController to load the"
                + " addTradeForm");
        TradeDTO trade = new TradeDTO();
        model.addAttribute(TRADE_ATTRIBUTE, trade);
        return "trade/add";
    }

    /**
     * Save a new Trade to the database.
     *
     * @param trade  the Trade to save
     * @param result the result
     * @param model  the model
     * @return the add form if Trade contains errors
     * or redirects user to the Trade home page
     */
    @PostMapping("/validate")
    public String validate(@Valid
                           @ModelAttribute("trade") final TradeDTO trade,
                           final BindingResult result,
                           final Model model) {
        LOGGER.debug("POST request sent from the TradeController"
                + " to save a new Trade");
        String addTradeUrl = ApiUrlConstants.TRADE_API_BASE_URL + "/add";
        if (!result.hasErrors()) {
            trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
            trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
            trade.setRevisionName(null);
            trade.setRevisionDate(null);
            HttpEntity<TradeDTO> httpEntity = new HttpEntity<>(trade);
            template.exchange(
                    addTradeUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            LOGGER.info("new Trade saved successfully by the TradeController");
            return REDIRECTION_LINK;
        } else {
            LOGGER.error("Failed to save the Trade. Add form reloaded");
            model.addAttribute(TRADE_ATTRIBUTE, trade);
            return "trade/add";
        }
    }

    /**
     * Loads an html form that allows updating existing Trades.
     *
     * @param id    the id of the Trade to update
     * @param model the model
     * @return the html form if the id is correct
     * or a 404 error page if the id is not correct
     */
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam final Integer id,
                                 final Model model) {
        LOGGER.debug("GET request sent from the TradeController to update"
                + " Trade {}", id);
        try {
            String findTradeById = ApiUrlConstants.TRADE_API_BASE_URL
                    + "/findById/" + id;
            ResponseEntity<TradeDTO> responseEntity = template.exchange(
                    findTradeById,
                    HttpMethod.GET,
                    null,
                    TradeDTO.class
            );
            if (responseEntity.hasBody()) {
                LOGGER.info("Update form loaded successfully");
                model.addAttribute(TRADE_ATTRIBUTE, responseEntity.getBody());
            }
        } catch (HttpServerErrorException e) {
            LOGGER.error("Failed to load Trade {}."
                    + " No matching resource is present", id);
            return "404NotFound/404";
        }
        return "trade/update";
    }

    /**
     * Save a Trade after update.
     *
     * @param id     the id of the Trade to update
     * @param trade  the new Trade information
     * @param result the result
     * @param model  the model
     * @return the Trade home page if the updated Trade has no errors
     * or the update form if the Trade contains errors
     */
    @PostMapping("/update/{id}")
    public String updateTrade(@PathVariable("id") final Integer id,
                              @Valid
                              @ModelAttribute("trade") final TradeDTO trade,
                              final BindingResult result,
                              final Model model) {
        LOGGER.debug("POST request sent from the Trade controller to update"
                + " Trade {}", id);
        String updateTradeUrl = ApiUrlConstants.TRADE_API_BASE_URL
                + "/update/" + id;
        if (!result.hasErrors()) {
            trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
            HttpEntity<TradeDTO> httpEntity = new HttpEntity<>(trade);
            template.exchange(
                    updateTradeUrl,
                    HttpMethod.PUT,
                    httpEntity,
                    String.class
            );
            LOGGER.info("Trade {} update successfully", id);
            return REDIRECTION_LINK;
        }
        model.addAttribute(TRADE_ATTRIBUTE, trade);
        LOGGER.error("Failed to update Trade {}. Update form reloaded", id);
        return "trade/update";
    }

    /**
     * Delete an existing Trade.
     *
     * @param id the id of the Trade to delete
     * @return the Trade home page if the id is correct
     * or a 404 error page if id is wrong
     */
    @GetMapping("/delete")
    public String deleteTrade(@RequestParam final Integer id) {
        LOGGER.debug("GET request sent from the"
                + " TradeController to delete Trade {}", id);
        String deleteTradeUrl = ApiUrlConstants.TRADE_API_BASE_URL
                + "/delete/" + id;
        try {
            template.exchange(
                    deleteTradeUrl,
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            LOGGER.info("Trade {} deleted successfully", id);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Deletion failed. No matching Trade resource"
                    + " for id: {}", id);
            return "404NotFound/404";
        }
        return REDIRECTION_LINK;
    }
}
