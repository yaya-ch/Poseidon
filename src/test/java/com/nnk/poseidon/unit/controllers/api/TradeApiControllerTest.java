package com.nnk.poseidon.unit.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.poseidon.converters.TradeConverter;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.services.TradeService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
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
@DisplayName("TradeApiController unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class TradeApiControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private TradeService service;

    @MockBean
    private TradeConverter converter;

    private Trade trade;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        trade = dataLoader.setTrade();
    }

    @DisplayName("GET: Retrieve Trade by id successfully")
    @Test
    void givenValidTradeId_whenFindById_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/trade/findById/1")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("GET: Retrieve all Trades from database")
    @Test
    void findAll_shouldReturnTradeList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/trade/findAll")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Save valid Trade successfully")
    @Test
    void givenValidTrade_whenSaveTrade_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/trade/add")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: Saving invalid Trade returns BadRequest Response")
    @Test
    void givenInvalidTrade_whenSaveTrade_thenResponseShouldBeBadRequest() throws Exception {
        trade.setType("");
        trade.setAccount("");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/trade/add")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("PUT: Update Trade successfully")
    @Test
    void givenValidTrade_whenUpdateTrade_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/trade/update/1")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("PUT: Using invalid Trade when updating returns BadRequest Response")
    @Test
    void givenInvalidTrade_whenUpdateTrade_thenResponseShouldBeBadRequest() throws Exception {
        trade.setAccount("");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/trade/update/1")
                .content(new ObjectMapper().writeValueAsString(trade))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("DELETE: Delete Trade successfully")
    @Test
    void givenValidTradeId_whenDeleteTrade_thenResponseShouldBeOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/trade/delete/1"))
                .andExpect(status().isOk());
    }


}