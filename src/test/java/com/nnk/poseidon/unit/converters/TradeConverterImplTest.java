package com.nnk.poseidon.unit.converters;

import com.nnk.poseidon.converters.TradeConverter;
import com.nnk.poseidon.converters.TradeConverterImpl;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Converters")
@DisplayName("TradeConverter unit tests")
class TradeConverterImplTest {

    private TradeConverter converter;

    private Trade trade;
    private TradeDTO tradeDTO;

    @BeforeEach
    void setUp() {
        ModelMapper mapper = new ModelMapper();
        converter = new TradeConverterImpl(mapper);
        DataLoader dataLoader = new DataLoader();
        trade = dataLoader.setTrade();
        tradeDTO = dataLoader.setTradeDTO();
    }

    @DisplayName("Convert TradeDTO to Trade entity")
    @Test
    void givenTradeDTO_whenTradeDTOToTradeEntityConverterIsCalled_thenDTOShouldBeConvertedToEntity() {
        Trade result = converter.tradeDTOToTradeEntityConverter(tradeDTO);

        assertNotNull(result);
        assertEquals(result.getAccount(), tradeDTO.getAccount());
        assertEquals(result.getType(), tradeDTO.getType());
        assertEquals(result.getTradeDate(), tradeDTO.getTradeDate());
    }

    @DisplayName("Convert a list of TradeDTOs to a list of Trade entities")
    @Test
    void givenTradeDTOList_whenTradeDTOListToTradeEntityListConverterIsCalled_theDTOListShouldBeConvertedToTradeEntityList() {
        List<TradeDTO> tradeDTOList = new ArrayList<>();
        tradeDTOList.add(tradeDTO);
        List<Trade> resultList = converter.tradeDTOListToTradeEntityListConverter(tradeDTOList);

        assertNotNull(resultList);
        assertEquals(resultList.size(), tradeDTOList.size());
        assertEquals(resultList.get(0).getDealName(), tradeDTOList.get(0).getDealName());
        assertEquals(resultList.get(0).getCreationDate(), tradeDTOList.get(0).getCreationDate());
        assertEquals(resultList.get(0).getRevisionDate(), tradeDTOList.get(0).getRevisionDate());
    }

    @DisplayName("Convert a Trade entity to TradeDTO")
    @Test
    void givenTradeEntity_whenTradeEntityToTradeDTOConverterIsCalled_thenEntityShouldBeConvertedToDTO() {
        TradeDTO result = converter.tradeEntityToTradeDTOConverter(trade);

        assertNotNull(result);
        assertEquals(result.getSecurity(), trade.getSecurity());
        assertEquals(result.getStatus(), trade.getStatus());
        assertEquals(result.getBenchmark(), trade.getBenchmark());
        assertEquals(result.getRevisionDate(), trade.getRevisionDate());
    }

    @DisplayName("Convert a list of Trade entities to a list of TradeDTOs")
    @Test
    void givenTradeList_whenTradeEntityListToTradeDTOListConverterIsCalled_thenEntityListShouldBeConvertedToDTOList() {
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade);

        List<TradeDTO> result = converter.tradeEntityListToTradeDTOListConverter(tradeList);
        assertNotNull(result);
        assertEquals(result.size(), tradeList.size());
        assertEquals(result.get(0).getSellPrice(), tradeList.get(0).getSellPrice());
    }
}