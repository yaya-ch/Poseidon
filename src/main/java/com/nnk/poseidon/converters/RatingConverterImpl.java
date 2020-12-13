package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements RatingConverter.
 *
 * @author Yahia CHERIFI
 * @see RatingConverter
 */
@Component
public class RatingConverterImpl implements RatingConverter {

    /**
     * ModelMapper to inject.
     */
    private final ModelMapper mapper;

    /**
     * Instantiates a new Rating converter.
     *
     * @param modelMapper the model mapper
     */
    public RatingConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * Converts a RatingDTO to a Rating entity.
     *
     * @param ratingDTO the RatingDTO to convert
     * @return a Rating entity
     */
    @Override
    public Rating ratingDTOToRatingEntityConverter(final RatingDTO ratingDTO) {
        return mapper.map(ratingDTO, Rating.class);
    }

    /**
     * Converts a RatingDTO List to a list of Rating entities.
     *
     * @param ratingDTOList the RatingDTO list to convert
     * @return a list of Rating entities
     */
    @Override
    public List<Rating> ratingDTOListToRatingEntityListConverter(
            final List<RatingDTO> ratingDTOList) {
        return ratingDTOList.stream()
                .map(this::ratingDTOToRatingEntityConverter)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Rating entity to a RatingDTO.
     *
     * @param rating the Rating entity to convert
     * @return a RatingDTO
     */
    @Override
    public RatingDTO ratingEntityToRatingDTOConverter(final Rating rating) {
        return mapper.map(rating, RatingDTO.class);
    }

    /**
     * Converts a list of Rating entities to a list of RatingDTOs.
     *
     * @param ratingList the Rating list to convert
     * @return a list of RatingDTOs
     */
    @Override
    public List<RatingDTO> ratingEntityListToRatingDTOListConverter(
            final List<Rating> ratingList) {
        return ratingList.stream()
                .map(this::ratingEntityToRatingDTOConverter)
                .collect(Collectors.toList());
    }
}
