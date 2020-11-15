package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.converters.BidListConverter;
import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.services.BidListService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

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
    private BidListService service;

    @MockBean
    private BidListConverter converter;

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
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidListList"))
                .andExpect(status().isOk());

        verify(service, times(1)).findAll();
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
        verify(service, times(1)).findAll();
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
        when(service.findBidListById(anyInt())).thenReturn(Optional.of(bidListDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update?id=6"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"))
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andExpect(status().isOk()).andReturn();
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
        verify(service, times(1)).findAll();
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
        verify(service, never()).save(any(BidList.class));
    }

    @DisplayName("DELETE bidListSuccessfully")
    @Test
    void deleteBid_shouldRedirectToBidListList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete?id=1"))
                .andExpect(redirectedUrl("/bidList/list"));
        verify(service, times(1)).deleteById(1);
    }
}

