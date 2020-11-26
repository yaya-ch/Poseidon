package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;

import java.util.List;

/**
 * This interface provides some abstract methods that allow.
 * conversion from/to Trade entity/TradeDTO
 *
 * @author Yahia CHERIFI
 */
public interface TradeConverter {

    /**
     * Converts a TradeDTO to a Trade entity.
     *
     * @param tradeDTO the TradeDTO that will be converted
     * @return a Trade entity
     */
    Trade tradeDTOToTradeEntityConverter(TradeDTO tradeDTO);

    /**
     * Converts a list of TradeDTOs to a list of Trade entities.
     *
     * @param tradeDTOList the TradeDTO list that will be converted
     * @return a list of Trade entities
     */
    List<Trade> tradeDTOListToTradeEntityListConverter(
            List<TradeDTO> tradeDTOList);

    /**
     * Converts a Trade entity to a TradeDTO.
     *
     * @param trade the Trade entity to convert
     * @return a TradeDTO
     */
    TradeDTO tradeEntityToTradeDTOConverter(Trade trade);

    /**
     * Converts a list of Trade entities to a list of TradeDTOs.
     *
     * @param tradeList the list of Trade entities to convert
     * @return a list of TradeDTO
     */
    List<TradeDTO> tradeEntityListToTradeDTOListConverter(
            List<Trade> tradeList);
}
