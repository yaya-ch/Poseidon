package com.nnk.poseidon.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.poseidon.unit.DataLoaderForUnitTests;
import com.nnk.poseidon.converters.BidListConverter;
import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.services.BidListService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yahia CHERIFI
 */

@Tag("ApiControllers")
@DisplayName("BidList api controller")
@AutoConfigureWebMvc
@SpringBootTest
class BidListApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private BidListService service;

    @MockBean
    private BidListConverter converter;

    private BidList bidList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoaderForUnitTests dataLoaderForUnitTests = new DataLoaderForUnitTests();
        bidList = dataLoaderForUnitTests.setBidList();
    }

    @DisplayName("Save bidList successfully")
    @Test
    void givenNewBidList_whenSaveBidListIsCalled_thenBidListShouldBeSaved() throws Exception {
        when(service.save(any(BidList.class))).thenReturn(bidList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bidList/add")
                .content(new ObjectMapper().writeValueAsString(bidList))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Update BidList successfully")
    @Test
    void givenBidListToUpdate_whenUpdateBidListIsCalled_thenBidListShouldBeUpdated() throws Exception{
        when(service.updateBidList(anyInt(), any(BidList.class))).thenReturn(bidList);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/bidList/update/1")
                .content(new ObjectMapper().writeValueAsString(bidList))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Find BidList by id")
    @Test
    void givenCorrectBidListId_whenFindByIdIsCalled_thenBidListShouldBeReturned() throws Exception {
        Optional<BidListDTO> bidListDTO = Optional.ofNullable(converter.bidListEntityToBidListDTO(bidList));
        when(service.findBidListById(anyInt())).thenReturn(bidListDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bidList/findById/1")
                .content(new ObjectMapper().writeValueAsString(bidList))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Find all BidLists")
    @Test
    void findAllBidLists_shouldReturnListOfBidListDTOs() throws Exception {
        List<BidList> bidListList = new ArrayList<>();
        bidListList.add(bidList);
        List<BidListDTO> bidListDTOList = converter.bidListEntitiesToBidListDTOs(bidListList);
        when(service.findAll()).thenReturn(bidListDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bidList/findAll"))
                .andExpect(status().isOk());
    }

    @DisplayName("Delete BidList by id")
    @Test
    void givenCorrectBidListId_whenDeleteByIdIsCalled_thenBidListShouldBeDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bidList/delete/1"))
                .andExpect(status().isOk());
    }
}