package com.nnk.poseidon.services;

import com.nnk.poseidon.converters.RatingConverter;
import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the CurvePointService.
 *
 * @author Yahia CHERIFI
 * @see RatingService
 */
@Service
public class RatingServiceImpl implements RatingService {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(RatingServiceImpl.class);
    /**
     * RatingRepository to inject.
     */
    private final RatingRepository repository;

    /**
     * RatingConverter to inject.
     */
    private final RatingConverter converter;

    /**
     * Instantiates a new RatingService.
     *
     * @param ratingRepository the rating repository
     * @param ratingConverter  the rating converter
     */
    @Autowired
    public RatingServiceImpl(final RatingRepository ratingRepository,
                             final RatingConverter ratingConverter) {
        this.repository = ratingRepository;
        this.converter = ratingConverter;
    }

    /**
     * Find Rating by id.
     *
     * @param id the Rating id
     * @return the Rating if found
     */
    @Override
    public Optional<RatingDTO> findRatingById(final Integer id) {
        Optional<Rating> findRating = repository.findById(id);
        if (findRating.isPresent()) {
            LOGGER.info("Rating {} loaded successfully", id);
            return Optional.ofNullable(converter
                    .ratingEntityToRatingDTOConverter(findRating.get()));
        } else {
            LOGGER.error("Failed to load Rating {}. No matching id found", id);
            throw new NoSuchElementException("No matching Rating is present");
        }
    }

    /**
     * Find all Ratings.
     *
     * @return a list of all the Ratings
     */
    @Override
    public List<RatingDTO> findAllRatings() {
        List<Rating> ratingList = repository.findAll();
        return converter.ratingEntityListToRatingDTOListConverter(ratingList);
    }

    /**
     * Save a new Rating.
     *
     * @param rating the Rating to save
     * @return a call to the repo layer
     */
    @Override
    public Rating saveRating(final Rating rating) {
        return repository.save(rating);
    }

    /**
     * Update an existing Rating.
     *
     * @param id     the id of the Rating to update
     * @param rating the new Rating information
     * @return a call to the repo layer
     */
    @Override
    public Rating updateRating(final Integer id, final Rating rating) {
        Optional<Rating> findRating = repository.findById(id);
        if (findRating.isPresent()) {
            rating.setId(findRating.get().getId());
            LOGGER.info("Rating {} updated successfully", id);
            return repository.save(rating);
        } else {
            LOGGER.error("Failed to retrieve Rating {}."
                    + " No matching results found", id);
            throw new NoSuchElementException("Failed to update Rating."
                    + " No matching id Found");
        }
    }

    /**
     * Delete an existing Rating.
     *
     * @param id the id of the Rating to delete
     */
    @Override
    public void deleteRating(final Integer id) {
        Optional<Rating> findRating = repository.findById(id);
        if (findRating.isPresent()) {
            LOGGER.info("Rating {} deleted successfully", id);
            repository.deleteById(id);
        } else {
            LOGGER.error("Failed to delete Rating{}."
                    + " No matching item found.", id);
            throw new NoSuchElementException("Deletion failed."
                    + " No matching item found in database");
        }
    }
}
