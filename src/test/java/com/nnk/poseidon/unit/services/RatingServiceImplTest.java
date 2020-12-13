package com.nnk.poseidon.unit.services;

import com.nnk.poseidon.converters.RatingConverter;
import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.repositories.RatingRepository;
import com.nnk.poseidon.services.RatingServiceImpl;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Services")
@DisplayName("RatingService unit tests")
class RatingServiceImplTest {

    private RatingServiceImpl service;

    @Mock
    private RatingRepository repository;

    @Mock
    private RatingConverter converter;

    private Rating rating;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RatingServiceImpl(repository, converter);
        DataLoader dataLoader = new DataLoader();
        rating = dataLoader.setRating();
    }

    @DisplayName("FindRatingById returns correct Rating")
    @Test
    void givenValidRatingId_whenFindRatingById_thenCorrectRatingShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(rating));
        Optional<RatingDTO> expected = service.findRatingById(1);
        assertNotNull(expected);
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("FindRatingById throws an exception")
    @Test
    void givenInvalidRatingId_whenFindRatingById_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findRatingById(1));
    }

    @DisplayName("Find all Ratings returns a list of Ratings")
    @Test
    void findAllRatings_shouldReturnAListOfAllRatings() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(rating);
        when(repository.findAll()).thenReturn(ratingList);
        List<RatingDTO> expected = service.findAllRatings();
        assertNotNull(expected);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("Save Rating successfully")
    @Test
    void givenNewRating_whenSaveRating_thenRatingShouldSaved() {
        when(repository.save(any(Rating.class))).thenReturn(rating);
        service.saveRating(rating);
        verify(repository, times(1)).save(rating);
    }

    @DisplayName("Update Rating successfully")
    @Test
    void givenRating_whenUpdateRating_thenRatingShouldBeSaved() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(rating));
        when(repository.save(any(Rating.class))).thenReturn(rating);
        service.updateRating(1, rating);
        verify(repository, times(1)).save(rating);
    }

    @DisplayName("Update Rating throws an exception")
    @Test
    void givenInvalidRatingId_whenUpdateRating_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.updateRating(1, rating));
        verify(repository, never()).save(rating);
    }

    @DisplayName("Delete Rating successfully")
    @Test
    void givenValidRatingId_whenDeleteRating_thenRatingShouldBeDeleted() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(rating));
        service.deleteRating(1);
        service.deleteRating(2);
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(1)).deleteById(2);
    }

    @DisplayName("Delete Rating throw an exception")
    @Test
    void givenInvalidRatingId_whenDeleteRating_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.deleteRating(1));
        verify(repository, never()).deleteById(1);
    }
}
