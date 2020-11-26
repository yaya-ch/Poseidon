package com.nnk.poseidon.services;

import com.nnk.poseidon.converters.TradeConverter;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import com.nnk.poseidon.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the TradeService interface.
 *
 * @author Yahia CHERIFI
 * @see TradeService
 */
@Service
public class TradeServiceImpl implements TradeService {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TradeServiceImpl.class);

    /**
     * TradeRepository to inject.
     */
    private final TradeRepository repository;

    /**
     * TradeConverter to inject.
     */
    private final TradeConverter converter;

    /**
     * Instantiates a new Trade service.
     *
     * @param tradeRepository the TradeRepository
     * @param tradeConverter  the TradeConverter
     */
    @Autowired
    public TradeServiceImpl(final TradeRepository tradeRepository,
                            final TradeConverter tradeConverter) {
        this.repository = tradeRepository;
        this.converter = tradeConverter;
    }

    /**
     * Find Trade by id.
     *
     * @param id the Trade id
     * @return the Trade if found
     */
    @Override
    public Optional<TradeDTO> findTradeById(final Integer id) {
        Optional<Trade> findTrade = repository.findById(id);
        if (findTrade.isPresent()) {
            LOGGER.info("Trade {} loaded successfully", id);
            return Optional.ofNullable(
                    converter.tradeEntityToTradeDTOConverter(findTrade.get()));
        } else {
            LOGGER.error("Failed to load Trade {}. No matching Trade matches",
                    id);
            throw new NoSuchElementException(
                    String.format("No resource found for Trade %s", id));
        }
    }

    /**
     * Find all Trades.
     *
     * @return a list of all the Trades
     */
    @Override
    public List<TradeDTO> findAllTrades() {
        List<Trade> retrieveAllTrades = repository.findAll();
        LOGGER.info("Trades retrieved successfully");
        return converter
                .tradeEntityListToTradeDTOListConverter(retrieveAllTrades);
    }

    /**
     * Save a new Trade.
     *
     * @param trade the Trade to save
     * @return a call to the repo layer
     */
    @Override
    public Trade saveTrade(final Trade trade) {
        Trade tradeToSave = repository.save(trade);
        LOGGER.info("Trade saved successfully");
        return tradeToSave;
    }

    /**
     * Update an existing Trade.
     *
     * @param id    the id of the Trade to update
     * @param trade the new Trade information
     * @return a call to the repo layer
     */
    @Override
    public Trade updateTrade(final Integer id, final Trade trade) {
        Optional<Trade> findTradeById = repository.findById(id);
        if (findTradeById.isPresent()) {
            LOGGER.info("Trade {} updated successfully", id);
            trade.setTradeId(findTradeById.get().getTradeId());
            trade.setCreationDate(findTradeById.get().getCreationDate());
            trade.setCreationName(findTradeById.get().getCreationName());
            return repository.save(trade);
        } else {
            LOGGER.error("Failed to update Trade {}."
                    + " No matching resource is present", id);
            throw new NoSuchElementException(String.format("Failed to update"
                    + " Trade %s. No matching Trade is present ", id));
        }
    }

    /**
     * Delete an existing Trade.
     *
     * @param id the id of the Trade to delete
     */
    @Override
    public void deleteTrade(final Integer id) {
        Optional<Trade> findTrade = repository.findById(id);
        if (findTrade.isPresent()) {
            repository.deleteById(id);
            LOGGER.info("Trade {} deleted successfully", id);
        } else {
            LOGGER.error("Failed to delete ");
            throw new NoSuchElementException(String.format("Failed to delete"
                    + " Trade %s. No matching trade is present", id));
        }
    }
}
