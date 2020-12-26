package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("ViewControllers")
@DisplayName("Rating view controller unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class RatingControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private RestTemplate template;

    private Rating rating;
    private RatingDTO ratingDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        rating = dataLoader.setRating();
        ratingDTO = dataLoader.setRatingDTO();
    }

    @DisplayName("Load the Rating home page")
    @Test
    void home_shouldReturnRatingHomePage_andAllRatings() throws Exception {
        List<RatingDTO> ratingDTOList = new ArrayList<>();
        ratingDTOList.add(ratingDTO);
        String findAllRatingsUrl = "http://localhost:8080/api/rating/findAll";
        when(template.exchange(
                findAllRatingsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>() { }
        )).thenReturn(new ResponseEntity<>(ratingDTOList, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ratingList"))
                .andExpect(view().name("rating/list"))
                .andExpect(status().isOk());
    }

    @DisplayName("Load the addForm html page")
    @Test
    void addRatingForm_shouldLoadTheAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"))
                .andExpect(view().name("rating/add"))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: save a valid Rating successfully")
    @Test
    void givenValidRating_whenSavingNewRating_thenResponseShouldBeOk_andRatingHomeShouldBeLoaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .contentType(MediaType.TEXT_HTML)
                .param("moodysRating", rating.getMoodysRating())
                .param("sandPRating", rating.getSandPRating())
                .param("fitchRating", rating.getFitchRating())
                .param("orderNumber", rating.getOrderNumber().toString()))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(redirectedUrl("/rating/list"))
                .andReturn();
    }

    @DisplayName("POST: saving invalid fails and loads the addForm")
    @Test
    void givenInvalidRating_whenSavingNewRating_thenAddRatingFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate")
                .contentType(MediaType.TEXT_HTML)
                .param("moodysRating", "")
                .param("sandPRating", rating.getSandPRating())
                .param("fitchRating", rating.getFitchRating())
                .param("orderNumber", rating.getOrderNumber().toString()))
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(view().name("rating/add"))
                .andReturn();
    }

    @DisplayName("Load the Rating update form")
    @Test
    void showUpdateForm_shouldLoadTheUpdateForm() throws Exception {
        String findById = "http://localhost:8080/api/rating/findById/1";
        when(template.exchange(
                findById,
                HttpMethod.GET,
                null,
                RatingDTO.class
        )).thenReturn(new ResponseEntity<>(ratingDTO, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/update?id=1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"))
                .andExpect(view().name("rating/update"))
                .andExpect(status().isOk()).andReturn();
    }

    @Disabled("This test will be refactored")
    @DisplayName("Invalid rating id loads 404 page instead of the RatingUpdateForm")
    @Test
    void givenInvalidRatingId_showUpdateForm_then404ErrorPageShouldBeLoaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/update?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andReturn();
    }

    @DisplayName("PUT: Update rating successfully and redirecting to the Rating home page")
    @Test
    void givenValidRating_whenUpdateRating_thenRequestShouldBeRedirectedToRatingHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
                .contentType(MediaType.TEXT_HTML)
                .param("moodysRating", rating.getMoodysRating())
                .param("sandPRating", rating.getSandPRating())
                .param("fitchRating", rating.getFitchRating())
                .param("orderNumber", rating.getOrderNumber().toString()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/rating/list"))
                .andReturn();
    }

    @DisplayName("PUT: saving invalid Rating fails and reloads the UpdateForm")
    @Test
    void givenInvalidRating_whenUpdateRating_thenUpdateRatingFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1")
                .contentType(MediaType.TEXT_HTML)
                .param("moodysRating", "")
                .param("sandPRating", rating.getSandPRating())
                .param("fitchRating", rating.getFitchRating())
                .param("orderNumber", rating.getOrderNumber().toString()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("rating/update"))
                .andReturn();
    }

    @DisplayName("DELETE: Delete a Rating successfully and redirecting to the Rating home page")
    @Test
    void givenValidRatingId_whenDeleteRating_thenResponseShouldBeRedirection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/delete?id=1"))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().is3xxRedirection()).andReturn();
    }

    @Disabled("This test will be refactored")
    @DisplayName("DELETE: invalid Rating id returns 404 error page")
    @Test
    void givenInvalidRatingId_whenDeleteRating_then404ErrorPageShouldBeLoaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/delete?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andReturn();
    }
}
