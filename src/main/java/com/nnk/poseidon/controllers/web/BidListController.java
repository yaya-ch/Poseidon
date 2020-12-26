package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.constants.ApiUrlConstants;
import com.nnk.poseidon.dto.BidListDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * The type Bid list controller.
 * This controller provide web services that allow CRUD operations on BidList
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/bidList")
public class BidListController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BidListController.class);

    /**
     * Static string for the BidList attribute.
     */
    private static final String BID_LIST_ATTRIBUTE = "bidList";

    /**
     * Static string for the redirection link.
     */
    private static final String REDIRECTION_TO_BID_LIST_LIST =
            "redirect:/bidList/list";

    /**
     * Static string for the BidList list attribute.
     */
    private static final String BID_LIST_LIST = "bidListList";

    /**
     * RestTemplate to inject.
     */
    private final RestTemplate template;

    /**
     * Instantiates a new Bid list controller.
     * @param restTemplate RestTemplate instance that is used for
     *                     consuming the API
     */
    @Autowired
    public BidListController(final RestTemplate restTemplate) {
        this.template = restTemplate;
    }

    /**
     * Home string.
     *
     * @param model the model
     * @return an html page that lists all the bidLists
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from the bidListController"
                + " to load all the BidLists");
        String findAllBidListsUrl = ApiUrlConstants.BID_LIST_API_BASE_URL
                + "/findAll";
        ResponseEntity<List<BidListDTO>> responseEntity = template.exchange(
                findAllBidListsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BidListDTO>>() { }
        );
        model.addAttribute(BID_LIST_LIST, responseEntity.getBody());
        return "bidList/list";
    }

    /**
     * Add bid form string.
     *
     * @param model the model
     * @return an html form that allows adding a new bidList
     */
    @GetMapping("/add")
    public String addBidForm(final Model model) {
        LOGGER.debug("GET request sent from the BidListController"
                + " to display the add BidList form");
        BidListDTO bidListToSave = new BidListDTO();
        model.addAttribute(BID_LIST_ATTRIBUTE, bidListToSave);
        return "bidList/add";
    }

    /**
     * Validate and save a new bidList.
     *
     * @param bidListDTO    the bidList to save
     * @param result the result
     * @param model  the model
     * @return the string
     */
    @PostMapping("/validate")
    public String validate(@Valid
                           @ModelAttribute("bidList")
                           final BidListDTO bidListDTO,
                           final BindingResult result,
                           final Model model) {
        LOGGER.debug("POST request sent from the BidLIstController"
                + " to save a new BidList");
        String addBidListUrl = ApiUrlConstants.BID_LIST_API_BASE_URL + "/add";
        if (!result.hasFieldErrors()) {
            bidListDTO.setBidListDate(
                    new Timestamp(System.currentTimeMillis()));
            bidListDTO.setCreationDate(
                    new Timestamp(System.currentTimeMillis()));
            bidListDTO.setRevisionName(null);
            bidListDTO.setRevisionDate(null);
            HttpEntity<BidListDTO> httpEntity = new HttpEntity<>(bidListDTO);
            template.exchange(
                    addBidListUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            return REDIRECTION_TO_BID_LIST_LIST;
        }
        LOGGER.error("Failed to validate The new BidList. AddForm reloaded");
        model.addAttribute(BID_LIST_ATTRIBUTE, bidListDTO);
        return "bidList/add";
    }

    /**
     * Show update form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") final Integer id,
                                 final Model model) {
        LOGGER.debug("GET request sent from the BidListController"
                + " to display the update form");
        try {
            String findBidIdUrl = ApiUrlConstants.BID_LIST_API_BASE_URL
                    + "/findById/" + id;
            ResponseEntity<BidListDTO> responseEntity = template.exchange(
                    findBidIdUrl,
                    HttpMethod.GET,
                    null,
                    BidListDTO.class
            );
            if (responseEntity.hasBody()) {
                LOGGER.info("BidList {} loaded successfully", id);
                model.addAttribute(BID_LIST_ATTRIBUTE,
                        responseEntity.getBody());
            }
        } catch (HttpStatusCodeException e) {
            LOGGER.error("Failed to load BidList {}."
                    + " No matching resource found", id);
            return "404NotFound/404";
        }
        return "bidList/update";
    }

    /**
     * Update bid string.
     *
     * @param id      the id
     * @param bidList the bid list
     * @param result  the result
     * @param model   the model
     * @return the string
     */
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") final Integer id,
                            @Valid @ModelAttribute("bidList")
                            final BidListDTO bidList,
                            final BindingResult result,
                            final Model model) {
        LOGGER.debug("POST request sent to from the BidListController"
                + " to update the BidList {}", id);
        String updateBidListUrl = ApiUrlConstants.BID_LIST_API_BASE_URL
                + "/update/" + id;
        if (!result.hasFieldErrors()) {
            bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
            HttpEntity<BidListDTO> httpEntity = new HttpEntity<>(bidList);
            template.exchange(
                    updateBidListUrl,
                    HttpMethod.PUT,
                    httpEntity,
                    String.class
            );
            return REDIRECTION_TO_BID_LIST_LIST;
        }
        LOGGER.error("Failed to validate BidList {}. Update form reloaded",
                bidList.getBidListId());
        model.addAttribute(BID_LIST_ATTRIBUTE, bidList);
        return "bidList/update";
    }

    /**
     * Delete bid string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/delete")
    public String deleteBid(@RequestParam("id") final Integer id) {
        LOGGER.debug("DELETE request sent from the BidListController"
                + " to delete BidList {}", id);
        try {
            String deleteBidListUrl = ApiUrlConstants.BID_LIST_API_BASE_URL
                    + "/delete/" + id;
            template.exchange(
                    deleteBidListUrl,
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            LOGGER.info("BidList {} deleted successfully", id);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Deletion failed. Failed to delete BidList {}", id);
            return "404NotFound/404";
        }
        return REDIRECTION_TO_BID_LIST_LIST;
    }
}
