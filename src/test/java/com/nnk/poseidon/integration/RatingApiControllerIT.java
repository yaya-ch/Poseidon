package com.nnk.poseidon.integration;

import com.nnk.poseidon.controllers.api.RatingApiController;
import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.repositories.RatingRepository;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("IntegrationTests")
@DisplayName("Rating api controller integration tests")
@AutoConfigureWebMvc
@SpringBootTest
@Sql(scripts = {"/sqlScriptsForITs/scriptForIT.sql"})
class RatingApiControllerIT {

    @Autowired
    private RatingApiController apiController;

    @Autowired
    private RatingRepository repository;

    private RatingDTO ratingDTO;

    @BeforeEach
    void setUp() {
        DataLoader dataLoader = new DataLoader();
        ratingDTO = dataLoader.setRatingDTO();
    }

    @DisplayName("Retrieve all Ratings from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAll_shouldReturnAllTheRatings() {
        List<RatingDTO> findAllRatings = apiController.findAll();
        assertNotNull(findAllRatings);
        assertEquals(1, findAllRatings.size());
        assertEquals(12, findAllRatings.get(0).getOrderNumber());
    }

    @DisplayName("Save a new Rating successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRating_whenSave_thenRatingShouldBeSaved() {
        apiController.saveRating(ratingDTO);
        List<Rating> findAll = repository.findAll();
        assertNotNull(findAll);
        assertEquals(2, findAll.size());
        assertEquals("Fitch rating", findAll.get(1).getFitchRating());
    }

    @DisplayName("Saving invalid Rating throws an exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidRating_whenSave_thenExceptionShouldBeThrown() {
        ratingDTO.setFitchRating("");
        assertThrows(Exception.class, () -> apiController.saveRating(ratingDTO));
    }

    @DisplayName("Update Rating successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRating_whenUpdate_thenRatingShouldBeUpdated() {
        ratingDTO.setFitchRating("new Fitch Rating");
        apiController.updateRating(5, ratingDTO);
        Optional<Rating> result = repository.findById(5);
        assertTrue(result.isPresent());
        assertEquals("new Fitch Rating", result.get().getFitchRating());
    }

    @DisplayName("Invalid Rating throws an exception when updating an existing Rating")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRating_whenUpdate_thenExceptionShouldBeThrown() {
        ratingDTO.setFitchRating("");
        assertThrows(Exception.class, () -> apiController.updateRating(5, ratingDTO));
    }

    @DisplayName("Delete Rating successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidRatingId_whenDelete_thenRatingShouldBeDeleted() {
        apiController.deleteRating(5);
        Optional<Rating> result = repository.findById(5);
        assertFalse(result.isPresent());
    }

    @DisplayName("Delete Rating throw exception when no Rating id matches")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidRatingId_whenDelete_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.updateRating(15, ratingDTO));
    }
}
