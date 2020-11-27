package com.nnk.poseidon.unit.controllers.web;

import com.nnk.poseidon.converters.TradeConverter;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import com.nnk.poseidon.services.TradeService;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("ViewControllers")
@DisplayName("Trade view controller unit tests")
@AutoConfigureWebMvc
@SpringBootTest
class TradeControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private TradeService service;

    @MockBean
    private TradeConverter converter;

    private Trade trade;
    private TradeDTO tradeDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        DataLoader dataLoader = new DataLoader();
        trade = dataLoader.setTrade();
        tradeDTO = dataLoader.setTradeDTO();
    }

    @DisplayName("Load Trade home page")
    @Test
    void home_shouldReturnTradeHomePage_AndAllTrades() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tradeList"))
                .andExpect(view().name("trade/list"))
                .andExpect(status().isOk());
        verify(service, times(1)).findAllTrades();
    }

    @DisplayName("Load the Trade add form")
    @Test
    void addTrade_shouldLoadTheAddForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/add"))
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/add"))
                .andExpect(status().isOk());
    }

    @DisplayName("POST: save a Trade successfully and return the Trade home page")
    @Test
    void givenValidTrade_whenSavingNewTrade_thenResponseShouldBeRedirectionToTradeHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .param("type", trade.getType())
                .param("account", trade.getAccount()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/trade/list"))
                .andReturn();
    }

    @DisplayName("POST: Reload the add form when Trade contains errors")
    @Test
    void givenInvalidTrade_whenSavingNewTrade_thenAddFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
                .param("type", "")
                .param("account", trade.getAccount()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("trade/add"))
                .andReturn();
    }

    @DisplayName("Load the update form")
    @Test
    void showUpdateForm_shouldLoadTheTradeUpdateForm() throws Exception {
        when(service.findTradeById(anyInt())).thenReturn(Optional.of(tradeDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/update?id=1"))
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/update"))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("Load 404 error page instead of update form when Trade id is incorrect")
    @Test
    void showUpdateForm_shouldLoad404ErrorPage() throws Exception {
        when(service.findTradeById(anyInt())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/update?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andExpect(status().isOk()).andReturn();
    }

    @DisplayName("Update Trade successfully")
    @Test
    void givenValidTrade_whenUpdateTrade_thenResponseShouldBeRedirectionToTradeHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
                .param("type", trade.getType())
                .param("account", trade.getAccount()))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/trade/list"))
                .andReturn();
    }

    @DisplayName("Update Trade returns the updateForm when Trade contains errors")
    @Test
    void givenInvalidTrade_whenUpdateTrade_thenUpdateFormShouldBeReloaded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1")
                .param("type", "")
                .param("account", trade.getAccount()))
                .andExpect(model().hasErrors())
                .andExpect(view().name("trade/update"))
                .andReturn();
    }

    @DisplayName("Delete Trade successfully")
    @Test
    void givenCorrectTradeId_whenDeleteTrade_thenResponseShouldBeRedirectionToTradeHomePage() throws Exception {
        when(service.findTradeById(anyInt())).thenReturn(Optional.of(tradeDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete?id=1"))
                .andExpect(redirectedUrl("/trade/list"))
                .andExpect(status().is3xxRedirection());
        verify(service, times(1)).deleteTrade(1);
    }

    @DisplayName("Delete Trade successfully")
    @Test
    void givenIncorrectTradeId_whenDeleteTrade_thenErrorPageShouldBeLoaded() throws Exception {
        when(service.findTradeById(anyInt())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete?id=1"))
                .andExpect(view().name("404NotFound/404"))
                .andExpect(status().isOk());
        verify(service, never()).deleteTrade(1);
    }
}
