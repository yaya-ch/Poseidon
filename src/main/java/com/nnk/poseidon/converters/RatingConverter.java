package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;

import java.util.List;

/**
 * This interface provides some abstract methods that allow.
 * conversion from/to Rating entity/RatingDTO
 *
 * @author Yahia CHERIFI
 */
public interface RatingConverter {

    /**
     * Converts a RatingDTO to a Rating entity.
     *
     * @param ratingDTO the RatingDTO to convert
     * @return a Rating entity
     */
    Rating ratingDTOToRatingEntityConverter(RatingDTO ratingDTO);

    /**
     * Converts a RatingDTO List to a list of Rating entities.
     *
     * @param ratingDTOList the RatingDTO list to convert
     * @return a list of Rating entities
     */
    List<Rating> ratingDTOListToRatingEntityListConverter(
            List<RatingDTO> ratingDTOList);

    /**
     * Converts a Rating entity to a RatingDTO.
     *
     * @param rating the Rating entity to convert
     * @return a RatingDTO
     */
    RatingDTO ratingEntityToRatingDTOConverter(Rating rating);

    /**
     * Converts a list of Rating entities to a list of RatingDTOs.
     *
     * @param ratingList the Rating list to convert
     * @return a list of RatingDTOs
     */
    List<RatingDTO> ratingEntityListToRatingDTOListConverter(
            List<Rating> ratingList);
}
