package com.nnk.poseidon.controllers.api;

import com.nnk.poseidon.converters.TradeConverter;
import com.nnk.poseidon.domain.Trade;
import com.nnk.poseidon.dto.TradeDTO;
import com.nnk.poseidon.services.TradeService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * This controller provides api endpoints.
 * that allow CRUD operations on Trade
 *
 * @author Yahia CHERIFI
 */
@RestController
@RequestMapping("/api/trade")
public class TradeApiController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(TradeApiController.class);

    /**
     * TradeService to inject.
     */
    private final TradeService service;

    /**
     * TradeConverter to inject.
     */
    private final TradeConverter converter;

    /**
     * Instantiates a new Trade api controller.
     *
     * @param tradeService   the trade service
     * @param tradeConverter the trade converter
     */
    @Autowired
    public TradeApiController(final TradeService tradeService,
                              final TradeConverter tradeConverter) {
        this.service = tradeService;
        this.converter = tradeConverter;
    }

    /**
     * Find a Trade by id.
     *
     * @param id the id of the Trade
     * @return the Trade if found
     */
    @ApiOperation(value = "Retrieve Trade by its id from database")
    @GetMapping("/findById/{id}")
    public Optional<TradeDTO> findById(@PathVariable final Integer id) {
        LOGGER.debug("GET request sent from the TradeApiController"
                + " to retrieve Trade {}", id);
        return service.findTradeById(id);
    }

    /**
     * Find all Trades.
     *
     * @return a list of all the existing Trades
     */
    @ApiOperation(value = "Retrieve all Trades from database")
    @GetMapping("/findAll")
    public List<TradeDTO> findAll() {
        LOGGER.debug("GET request sent from the TradeApiController"
                + " to retrieve all Trades");
        return service.findAllTrades();
    }

    /**
     * Save a new Trade.
     *
     * @param trade the Trade to save
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Save a new Trade to database")
    @PostMapping("/add")
    public String saveTrade(@Valid @RequestBody final TradeDTO trade) {
        LOGGER.debug("POST request sent from the TradeApiController"
                + " to save a new Trades {}", trade.getTradeId());
        trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
        trade.setRevisionName(null);
        trade.setRevisionDate(null);
        Trade tradeToSave = converter.tradeDTOToTradeEntityConverter(trade);
        service.saveTrade(tradeToSave);
        return "Trade saved successfully";
    }

    /**
     * Update an existing Trade.
     *
     * @param id       the id of the Trade to update
     * @param tradeDTO the new Trade information
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Update an existing Trade")
    @PutMapping("/update/{id}")
    public String updateTrade(@PathVariable final Integer id,
                              @Valid @RequestBody final TradeDTO tradeDTO) {
        LOGGER.debug("PUT request sent from the TradeApiController"
                + " to update Trade {}", id);
        tradeDTO.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        Trade tradeToUpdate =
                converter.tradeDTOToTradeEntityConverter(tradeDTO);
        service.updateTrade(id, tradeToUpdate);
        return String.format("Trade %s Updated successfully", id);
    }

    /**
     * Delete an existing Trade.
     *
     * @param id the id of the Trade to delete
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Delete an existing Trade from database")
    @DeleteMapping("/delete/{id}")
    public String deleteTrade(@PathVariable final Integer id) {
        LOGGER.debug("DELETE request sent from the TradeApiController"
                + " to delete Trade {}", id);
        service.deleteTrade(id);
        return String.format("Trade %s deleted Successfully", id);
    }
}
