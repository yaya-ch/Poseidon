package com.nnk.poseidon.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.poseidon.converters.RatingConverter;
import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.services.RatingService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yahia CHERIFI
 */

@Tag("ApiControllers")
@DisplayName("RatingApiController unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class RatingApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private RatingService service;
    @MockBean
    private RatingConverter converter;

    private Rating rating;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        rating = dataLoader.setRating();
    }

    @DisplayName("GET: Find Rating by id")
    @Test
    void givenValidRatingId_whenFindById_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rating/findById/1")
                .content(new ObjectMapper().writeValueAsString(rating))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @DisplayName("GET: Retrieve all the Ratings from database")
    @Test
    void findAll_shouldReturnAListOfAllRatings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rating/findAll")
                .content(new ObjectMapper().writeValueAsString(rating))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Save Rating successfully")
    @Test
    void givenValidRatingToSave_whenSaveRating_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/rating/add")
                .content(new ObjectMapper().writeValueAsString(rating))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Save invalid Rating returns Bad Request response")
    @Test
    void givenInvalidRating_whenSaveRating_thenResponseShouldBeBadRequest() throws Exception {
        rating.setFitchRating("");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/rating/add")
                .content(new ObjectMapper().writeValueAsString(rating))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("PUT: Update Rating successfully")
    @Test
    void givenValidRating_updateRating_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/rating/update/1")
                .content(new ObjectMapper().writeValueAsString(rating))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("PUT: Updating Rating returns Bad Request response")
    @Test
    void givenInvalidRating_whenUpdateRating_thenResponseShouldBeBadRequest() throws Exception {
        rating.setMoodysRating("");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/rating/update/1")
                .content(new ObjectMapper().writeValueAsString(rating))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("DELETE: Delete Rating successfully")
    @Test
    void givenValidRatingId_whenDeleteRating_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rating/delete/1"))
                .andExpect(status().isOk());
    }
}
