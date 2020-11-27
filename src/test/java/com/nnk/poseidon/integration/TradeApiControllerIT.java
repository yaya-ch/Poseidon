package com.nnk.poseidon.integration;

import com.nnk.poseidon.controllers.api.TradeApiController;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import com.nnk.poseidon.repositories.TradeRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("IntegrationTests")
@DisplayName("Trade api controller integration tests")
@AutoConfigureWebMvc
@SpringBootTest
@Sql(scripts = {"/sqlScriptsForITs/scriptForIT.sql"})
class TradeApiControllerIT {

    @Autowired
    private TradeApiController apiController;

    @Autowired
    private TradeRepository repository;

    private TradeDTO tradeDTO;
    @BeforeEach
    void setUp() {
        DataLoader dataLoader = new DataLoader();
        tradeDTO = dataLoader.setTradeDTO();
    }

    @DisplayName("Retrieve all Trades from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAll_shouldReturnAllTrades() {
        List<TradeDTO> result = apiController.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("trade db deal name", result.get(0).getDealName());
    }

    @DisplayName("Retrieve Trade by id from database")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenCorrectTradeId_whenFindById_thenCorrectTradeShouldBeReturned() {
        Optional<TradeDTO> expected = apiController.findById(44);
        assertTrue(expected.isPresent());
        assertEquals("trade db account", expected.get().getAccount());
    }

    @DisplayName("FindById throws exception when Trade id is incorrect")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenIncorrectTradeId_whenFindById_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.findById(1));
    }

    @DisplayName("Save a new Trade successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidTrade_whenSaveTrade_thenTradeShouldBeSaved() {
        apiController.saveTrade(tradeDTO);
        List<Trade> result = repository.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(result.get(1).getAccount(), tradeDTO.getAccount());
    }

    @DisplayName("Save an invalid Trade throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidTrade_whenSaveTrade_thenExceptionShouldBeThrow() {
        tradeDTO.setType("");
        assertThrows(Exception.class, () -> apiController.saveTrade(tradeDTO));
    }

    @DisplayName("Update an existing Trade successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidTrade_whenUpdateTrade_thenTradeShouldBeUpdated() {
        apiController.updateTrade(44, tradeDTO);
        Optional<Trade> expected = repository.findById(44);
        assertTrue(expected.isPresent());
        assertEquals(expected.get().getType(), tradeDTO.getType());
        assertEquals(expected.get().getAccount(), tradeDTO.getAccount());
    }

    @DisplayName("Use Invalid Trade for updating an existing one throws and exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidTrade_whenUpdateTrade_thenExceptionShouldBeThrown() {
        tradeDTO.setAccount("");
        assertThrows(Exception.class, () -> apiController.updateTrade(44, tradeDTO));
    }

    @DisplayName("Updating Trade throws exception when Trade id is incorrect")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidTradeId_whenUpdateTrade_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.updateTrade(22, tradeDTO));
    }

    @DisplayName("Delete an existing Trade successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidTradeIt_whenDeleteTrade_thenTradeShouldBeDeleted() {
        apiController.deleteTrade(44);
        Optional<Trade> expected = repository.findById(44);
        assertFalse(expected.isPresent());
    }

    @DisplayName("Deleting a Trade with wrong id throws an exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidTradeIt_whenDeleteTrade_thenExceptionShouldBeThrown() {
        assertThrows(Exception.class, () -> apiController.deleteTrade(23));
    }
}
