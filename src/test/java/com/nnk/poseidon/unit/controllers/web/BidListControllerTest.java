package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.dto.BidListDTO;
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
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("ViewControllers")
@DisplayName("BidList view controller")
@AutoConfigureWebMvc
@SpringBootTest
class BidListControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private RestTemplate template;

    private BidListDTO bidListDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoaderForUnitTests = new DataLoader();
        bidListDTO = dataLoaderForUnitTests.setBidListDTO();
    }

    @DisplayName("List all bidLists in the bidList home page")
    @Test
    void home_shouldReturnHomePage() throws Exception {
        List<BidListDTO> bidListDTOList = new ArrayList<>();
        bidListDTOList.add(bidListDTO);
        String homeUrl = "http://localhost:8080/api/bidList/findAll";
        when(template.exchange(
                homeUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BidListDTO>>() {}))
                .thenReturn(new ResponseEntity<>(bidListDTOList, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidListList"))
                .andExpect(status().isOk());
    }

    @DisplayName("Show the add form")
    @Test
    void addBidForm_shouldReturnAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/add"))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("bidList")).andExpect(status().isOk());
    }

    @DisplayName("Post valid BidList successfully")
    @Test
    void givenValidBidListDto_whenValidIsCalled_thenRedirectToHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .contentType(MediaType.TEXT_HTML)
                .param("type", bidListDTO.getType())
                .param("account", bidListDTO.getAccount())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(redirectedUrl("/bidList/list"))
                .andReturn();
    }

    @DisplayName("POST invalid will return the add form")
    @Test
    void givenInvalidBidListDto_whenValidIsCalled_thenRedirectToAddForm() throws Exception {
        bidListDTO.setType("");
        bidListDTO.setAccount("");
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate")
                .contentType(MediaType.TEXT_HTML)
                .param("type", bidListDTO.getType())
                .param("account", bidListDTO.getAccount())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(view().name("bidList/add"))
                .andReturn();
    }

    @DisplayName("GET the update form")
    @Test
    void showUpdateForm_shouldReturnTheUpdateForm() throws Exception {
        String findByIdUrl = "http://localhost:8080/api/bidList/findById/6";
        when(template.exchange(
                findByIdUrl,
                HttpMethod.GET,
                null,
                BidListDTO.class
        )).thenReturn(new ResponseEntity<>(bidListDTO, HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update?id=6"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"))
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("GET the update form returns 404 error page")
    @Test
    void showUpdateForm_shouldReturn404ErrorPage() throws Exception {
        String findByIdUrl = "http://localhost:8080/api/bidList/findById/6";
        when(template.exchange(
                findByIdUrl,
                HttpMethod.GET,
                null,
                BidListDTO.class
        )).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update?id=6"))
                .andExpect(MockMvcResultMatchers.view().name("404NotFound/404"));
    }

    @DisplayName("UPDATE valid BidList successfully")
    @Test
    void givenValidBidListDto_whenUpdateBidIsCalled_thenRedirectToHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/6")
                .contentType(MediaType.TEXT_HTML)
                .param("type", bidListDTO.getType())
                .param("account", bidListDTO.getAccount())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(redirectedUrl("/bidList/list"))
                .andReturn();
    }

    @DisplayName("UPDATE invalid BidList returns the Update form")
    @Test
    void givenInvalidBidListDto_whenUpdateBidIsCalled_thenRedirectToHome() throws Exception {
        bidListDTO.setType("");
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/6")
                .contentType(MediaType.TEXT_HTML)
                .param("type", bidListDTO.getType())
                .param("account", bidListDTO.getAccount())
                .param("bidQuantity", bidListDTO.getBidQuantity().toString()))
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andReturn();
    }

    @DisplayName("DELETE bidListSuccessfully")
    @Test
    void deleteBid_shouldRedirectToBidListList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete?id=1"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("DELETE bidList returns 404 error page")
    @Test
    void deleteBid_shouldReturn404ErrorPage() throws Exception {
        String deleteBidUrl = "http://localhost:8080/api/bidList/delete/1";
        when(template.exchange(
                deleteBidUrl,
                HttpMethod.DELETE,
                null,
                String.class)).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete?id=1"))
                .andExpect(view().name("404NotFound/404"));
    }
}
