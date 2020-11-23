package com.nnk.poseidon.services;

import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;

import java.util.List;
import java.util.Optional;

/**
 * The interface RatingService.
 * It contains abstract methods that provide
 * the logic to operated on the data sent to and from
 * the Rating controllers and the repository layer
 *
 * @author Yahia CHERIFI
 */

public interface RatingService {

    /**
     * Find Rating by id optional.
     *
     * @param id the Rating id
     * @return the Rating if found
     */
    Optional<RatingDTO> findRatingById(Integer id);

    /**
     * Find all Ratings.
     *
     * @return a list of all the Ratings
     */
    List<RatingDTO> findAllRatings();

    /**
     * Save a new Rating.
     *
     * @param rating the Rating to save
     * @return a call to the repo layer
     */
    Rating saveRating(Rating rating);

    /**
     * Update an existing Rating.
     *
     * @param id     the id of the Rating to update
     * @param rating the new Rating information
     * @return a call to the repo layer
     */
    Rating updateRating(Integer id, Rating rating);

    /**
     * Delete an existing Rating.
     *
     * @param id the id of the Rating to delete
     */
    void deleteRating(Integer id);
}
