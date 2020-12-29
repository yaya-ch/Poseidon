package com.nnk.poseidon.controllers.web;

import com.nnk.poseidon.constants.ApiUrlConstants;
import com.nnk.poseidon.dto.RatingDTO;
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

/**
 * The type Rating controller.
 * This controller provide web services that allow CRUD operations on Rating
 *
 * @author Yahia CHERIFI
 */

@Controller
@RequestMapping("/rating")
public class RatingController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(RatingController.class);

    /**
     * Static string for the rating attribute.
     */
    private static final String RATING_ATTRIBUTE = "rating";

    /**
     * Static string for the redirecting link.(to the Rating home page).
     */
    private static final String REDIRECTION_LINK = "redirect:/rating/list";

    /**
     * RestTemplate to inject.
     */
    private final RestTemplate template;

    /**
     * Instantiates a new Rating controller.
     * @param restTemplate RestTemplate instance that is used for
     *                 consuming the API
     */
    @Autowired
    public RatingController(final RestTemplate restTemplate) {
        this.template = restTemplate;
    }

    /**
     * The rating home page that displays all the ratings.
     *
     * @param model the model
     * @return an html page that displays all the Ratings
     */
    @GetMapping("/list")
    public String home(final Model model) {
        LOGGER.debug("GET request sent from the home method of the"
                + " RatingController to load the html home page and display"
                + " all the ratings");
        String findAllRatingsUrl = ApiUrlConstants.RATING_API_BASE_URL
                + "/findAll";
        ResponseEntity<List<RatingDTO>> responseEntity = template.exchange(
                findAllRatingsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>() { }
        );
        model.addAttribute("ratingList", responseEntity.getBody());
        return "rating/list";
    }

    /**
     * Load the addForm that allows adding a new Rating.
     *
     * @param model the model
     * @return the html template that displays the form
     */
    @GetMapping("/add")
    public String addRatingForm(final Model model) {
        LOGGER.debug("GET request sent from addRatingForm of the"
                + " RatingController to load the html add form");
        RatingDTO rating = new RatingDTO();
        model.addAttribute(RATING_ATTRIBUTE, rating);
        return "rating/add";
    }

    /**
     * Save a new Rating to the database.
     *
     * @param ratingDTO the Rating to save
     * @param result    the result
     * @param model     the model
     * @return the addRatingForm if Rating contains errors
     * redirects the user to Rating Home page if Rating has no errors
     */
    @PostMapping("/validate")
    public String validate(@Valid
                           @ModelAttribute("rating") final RatingDTO ratingDTO,
                           final BindingResult result,
                           final Model model) {
        LOGGER.debug("POST request sent from the validate method of the"
                + " RatingController to add a new Rating {}",
                ratingDTO.getId());
        String addRatingUrl = ApiUrlConstants.RATING_API_BASE_URL + "/add";
        if (!result.hasErrors()) {
            HttpEntity<RatingDTO> httpEntity = new HttpEntity<>(ratingDTO);
            template.exchange(
                    addRatingUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            LOGGER.info("Rating {} saved successfully", ratingDTO.getId());
            return REDIRECTION_LINK;
        }
        LOGGER.error("Failed to save Rating {}. Loading the addForm again.",
                ratingDTO.getId());
        model.addAttribute(RATING_ATTRIBUTE, ratingDTO);
        return "rating/add";
    }

    /**
     * Load the update form that allows updating an existing Rating.
     *
     * @param id    the id of the Rating to update
     * @param model the model
     * @return the an error html page if no matching element was found
     * or a form that allows updating an existing Rating
     */
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") final Integer id,
                                 final Model model) {
        LOGGER.debug("GET request sent from the show UpdateForm of the"
                + " RatingController to load the update form and update"
                + " Rating {}", id);
        try {
            String findRatingById = ApiUrlConstants.RATING_API_BASE_URL
                    + "/findById/" + id;
            ResponseEntity<RatingDTO> responseEntity = template.exchange(
                    findRatingById,
                    HttpMethod.GET,
                    null,
                    RatingDTO.class
            );
            model.addAttribute(RATING_ATTRIBUTE, responseEntity.getBody());
            LOGGER.info("Update form loaded successfully"
                    + " to update Rating {}", id);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Failed to load Rating {}. No matching item present",
                    id);
            return "404NotFound/404";
        }
        return "rating/update";
    }

    /**
     * Save a Rating after update.
     *
     * @param id     the id of the Rating to update
     * @param rating the new Rating information
     * @param result the result
     * @param model  the model
     * @return the update form if Rating contains errors
     * or redirects the user to the Rating home page
     */
    @PostMapping("/update/{id}")
    public String updateRating(@PathVariable("id") final Integer id,
                               @Valid
                               @ModelAttribute("rating")
                               final RatingDTO rating,
                               final BindingResult result, final Model model) {
        LOGGER.debug("POST request sent from the validate method of the"
                + " RatingController to update Rating {}", id);
        String updateRatingUrl = ApiUrlConstants.RATING_API_BASE_URL
                + "/update/" + id;
        if (!result.hasErrors()) {
            HttpEntity<RatingDTO> httpEntity = new HttpEntity<>(rating);
            template.exchange(
                    updateRatingUrl,
                    HttpMethod.PUT,
                    httpEntity,
                    String.class
            );
            LOGGER.info("Rating {} updated successfully", id);
            return REDIRECTION_LINK;
        }
        LOGGER.error("Failed to validate Rating {}. UpdateForm reloaded", id);
        model.addAttribute(RATING_ATTRIBUTE, rating);
        return "rating/update";
    }

    /**
     * Delete an existing Rating.
     *
     * @param id the id of the Rating to delete
     * @return an Error page if no id matches
     * or Redirect to the Rating home page
     */
    @GetMapping("/delete")
    public String deleteRating(@RequestParam("id") final Integer id) {
        LOGGER.debug("GET request sent from the deleteRating method of the"
                + " RatingController to delete Rating {}", id);
        String deleteRating = ApiUrlConstants.RATING_API_BASE_URL
                + "/delete/" + id;
        try {
            template.exchange(
                    deleteRating,
                    HttpMethod.DELETE,
                    null,
                    String.class
            );
            LOGGER.info("Rating {} delete successfully."
                    + " Redirecting to the Rating home page", id);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Failed to delete Rating {}."
                    + " No matching element present", id);
            return "404NotFound/404";
        }
        return REDIRECTION_LINK;
    }
}
