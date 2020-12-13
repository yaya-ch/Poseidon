package com.nnk.poseidon.unit.converters;

import com.nnk.poseidon.converters.RatingConverter;
import com.nnk.poseidon.converters.RatingConverterImpl;
import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Converters")
@DisplayName("RatingConverter unit tests")
class RatingConverterImplTest {

    private RatingConverter converter;

    private Rating rating;
    private RatingDTO ratingDTO;

    @BeforeEach
    void setUp() {
        converter = new RatingConverterImpl(new ModelMapper());
        DataLoader dataLoader = new DataLoader();
        rating = dataLoader.setRating();
        ratingDTO = dataLoader.setRatingDTO();

    }

    @DisplayName("Convert RatingDTO to Rating entity")
    @Test
    void givenRatingDTO_whenRatingDTOToRatingEntityConverterCalled_thenDTOShouldBeConverted() {
        Rating result = converter.ratingDTOToRatingEntityConverter(ratingDTO);
        assertNotNull(result);
        assertEquals(result.getId(), ratingDTO.getId());
        assertEquals(result.getOrderNumber(), ratingDTO.getOrderNumber());
    }

    @DisplayName("Convert a list of RatingDTOs to a list of Rating entities")
    @Test
    void givenRatingDTOList_whenRatingDTOListToRatingEntityListConverterCalled_thenDTOListShouldBeConverted() {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        ratingDTOList.add(ratingDTO);
        List<Rating> result = converter.ratingDTOListToRatingEntityListConverter(ratingDTOList);
        assertEquals(result.size(), ratingDTOList.size());
        assertEquals(result.get(0).getId(), ratingDTOList.get(0).getId());
        assertEquals(result.get(0).getMoodysRating(), ratingDTOList.get(0).getMoodysRating());
        assertEquals(result.get(0).getSandPRating(), ratingDTOList.get(0).getSandPRating());
        assertEquals(result.get(0).getFitchRating(), ratingDTOList.get(0).getFitchRating());
        assertEquals(result.get(0).getOrderNumber(), ratingDTOList.get(0).getOrderNumber());
    }

    @DisplayName("Convert Rating entity to RatingDTO")
    @Test
    void ratingEntityToRatingDTOConverter() {
        RatingDTO result = converter.ratingEntityToRatingDTOConverter(rating);
        assertNotNull(result);
        assertEquals(result.getId(), rating.getId());
        assertEquals(result.getMoodysRating(), rating.getMoodysRating());
        assertEquals(result.getSandPRating(), rating.getSandPRating());
        assertEquals(result.getFitchRating(), result.getFitchRating());
        assertEquals(result.getOrderNumber(), rating.getOrderNumber());
    }

    @DisplayName("Convert a list of Rating entities to a list of RatingDTOs")
    @Test
    void ratingEntityListToRatingDTOListConverter() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        List<RatingDTO> result = converter.ratingEntityListToRatingDTOListConverter(ratingList);
        assertNotNull(result);
        assertEquals(result.size(), ratingList.size());
        assertEquals(result.get(0).getOrderNumber(), ratingList.get(0).getOrderNumber());
    }
}