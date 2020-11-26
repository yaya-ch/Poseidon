package com.nnk.poseidon.unit.services;

import com.nnk.poseidon.converters.TradeConverter;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import com.nnk.poseidon.repositories.TradeRepository;
import com.nnk.poseidon.services.TradeService;
import com.nnk.poseidon.services.TradeServiceImpl;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
@DisplayName("TradeService unit tests")
class TradeServiceImplTest {

    private TradeService service;

    @Mock
    private TradeRepository repository;

    @Mock
    private TradeConverter converter;

    private Trade trade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new  TradeServiceImpl(repository, converter);
        DataLoader dataLoader = new DataLoader();
        trade = dataLoader.setTrade();
    }

    @DisplayName("FindTradeById returns correct Trade")
    @Test
    void givenValidTradeId_whenFindTradeById_thenTradeShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(trade));
        Optional<TradeDTO> expected = service.findTradeById(1);
        assertNotNull(expected);
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("FindTradeById throws an exception")
    @Test
    void givenInvalidTradeId_whenFindTradeById_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findTradeById(1));
    }

    @DisplayName("Find all Trade returns a list of Trades")
    @Test
    void findAllTrades_shouldReturnAListOfAllTrades() {
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade);
        when(repository.findAll()).thenReturn(tradeList);
        List<TradeDTO> expected = service.findAllTrades();
        assertNotNull(expected);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("Save Trade successfully")
    @Test
    void givenTradeToSave_whenSaveTrade_thenTradeShouldBeSaved() {
        when(repository.save(any(Trade.class))).thenReturn(trade);
        service.saveTrade(trade);
        verify(repository, times(1)).save(trade);
    }

    @Test
    void givenValidTradeId_whenUpdateTrade_thenTradeShouldBeSaved() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(trade));
        service.updateTrade(1, trade);
        verify(repository, times(1)).save(trade);
    }

    @DisplayName("Update Trade throws an exception")
    @Test
    void givenInvalidTradeId_whenUpdateTrade_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.updateTrade(1, trade));
        verify(repository, never()).save(trade);
    }

    @DisplayName("Delete Trade successfully")
    @Test
    void givenCorrectTradeId_whenDeleteTrade_thenTradeShouldBeDeleted() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(trade));
        service.deleteTrade(1);
        service.deleteTrade(2);
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(1)).deleteById(2);
    }

    @DisplayName("Delete Trade throws an exception")
    @Test
    void givenIncorrectTradeId_whenDeleteTrade_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.deleteTrade(1));
        verify(repository, never()).deleteById(1);
    }
}