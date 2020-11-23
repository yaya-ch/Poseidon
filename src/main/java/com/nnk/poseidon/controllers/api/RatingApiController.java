package com.nnk.poseidon.controllers.api;

import com.nnk.poseidon.converters.RatingConverter;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.services.RatingService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * This controller provides api endpoints.
 * that allow CRUD operations on Rating
 *
 * @author Yahia CHERIFI
 */

@RestController
@RequestMapping("/api/rating")
public class RatingApiController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(RatingApiController.class);

    /**
     * RatingService to inject.
     */
    private final RatingService service;

    /**
     * RatingConverter to inject.
     */
    private final RatingConverter converter;

    /**
     * Instantiates a new RatingApi controller.
     *
     * @param ratingService   the rating service
     * @param ratingConverter the rating converter
     */
    @Autowired
    public RatingApiController(final RatingService ratingService,
                               final RatingConverter ratingConverter) {
        this.service = ratingService;
        this.converter = ratingConverter;
    }

    /**
     * Find rating by id.
     *
     * @param id the Rating id
     * @return the the Rating if found
     */
    @ApiOperation(value = "Retrieve Rating by its id from database")
    @GetMapping("/findById/{id}")
    public Optional<RatingDTO> findRatingById(@PathVariable final Integer id) {
        LOGGER.debug("GET request sent from the findRatingById of the"
                + " RatingApiController to retrieve Rating {}", id);
        return service.findRatingById(id);
    }

    /**
     * Retrieve all Ratings.
     *
     * @return a list of all the existing Ratings
     */
    @ApiOperation(value = "Retrieve all Ratings from database")
    @GetMapping("/findAll")
    public List<RatingDTO> findAll() {
        LOGGER.debug("GET request sent from the findRatingById of the"
                + " RatingApiController to retrieve all the Ratings");
        return service.findAllRatings();
    }

    /**
     * Save a new Rating.
     *
     * @param ratingDTO the Rating to save
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Save a new Rating to database")
    @PostMapping("/add")
    public String saveRating(@RequestBody @Valid final RatingDTO ratingDTO) {
        LOGGER.debug("POST request sent from the findRatingById of the"
                + " RatingApiController to save a new Rating");
        service.saveRating(converter
                .ratingDTOToRatingEntityConverter(ratingDTO));
        return "Rating saved successfully";
    }

    /**
     * Update an Existing Rating.
     *
     * @param id        the id of the Rating to update
     * @param ratingDTO the new Rating information
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Update an existing Rating")
    @PutMapping("/update/{id}")
    public String updateRating(@PathVariable final Integer id,
                               @RequestBody @Valid final RatingDTO ratingDTO) {
        LOGGER.debug("Put request sent from the updateRating method of the"
                + " RatingApiController to update Rating {}", id);
        service.updateRating(id,
                converter.ratingDTOToRatingEntityConverter(ratingDTO));
        return "Rating updated successfully";
    }

    /**
     * Delete an existing Rating by id.
     *
     * @param id the id of the Rating to Delete
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Delete an existing Rating from database")
    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable final Integer id) {
        LOGGER.debug("DELETE request sent from the deleteRating method of the"
                + " RatingApiController to delete Rating {}", id);
        service.deleteRating(id);
        return "Rating deleted successfully";
    }
}
