package com.nnk.poseidon.services;

import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;

import java.util.List;
import java.util.Optional;

/**
 * The interface TradeService.
 * It contains abstract methods that provide
 * the logic to operated on the data sent to and from
 * the Trade controllers and the repository layer
 *
 * @author Yahia CHERIFI
 */
public interface TradeService {

    /**
     * Find Trade by id.
     *
     * @param id the Trade id
     * @return the Trade if found
     */
    Optional<TradeDTO> findTradeById(Integer id);

    /**
     * Find all Trades.
     *
     * @return a list of all the Trades
     */
    List<TradeDTO> findAllTrades();

    /**
     * Save a new Trade.
     *
     * @param trade the Trade to save
     * @return a call to the repo layer
     */
    Trade saveTrade(Trade trade);

    /**
     * Update an existing Trade.
     *
     * @param id    the id of the Trade to update
     * @param trade the new Trade information
     * @return a call to the repo layer
     */
    Trade updateTrade(Integer id, Trade trade);

    /**
     * Delete an existing Trade.
     *
     * @param id the id of the Trade to delete
     */
    void deleteTrade(Integer id);
}
