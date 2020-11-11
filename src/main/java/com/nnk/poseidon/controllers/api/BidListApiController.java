package com.nnk.poseidon.controllers.api;

import com.nnk.poseidon.converters.BidListConverter;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

/**
 * This controller provides api endpoints.
 * that allow CRUD operations on BidList
 *
 * @author Yahia CHERIFI
 */

@RestController
@RequestMapping("/api/bidList")
public class BidListApiController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BidListApiController.class);

    /**
     * BidListService to inject.
     */
    private final BidListService service;

    /**
     * BidListConverter to inject.
     */
    private final BidListConverter converter;

    /**
     * Instantiates a new Bid list api controller.
     *
     * @param bidListService   the bid list service
     * @param bidListConverter the bid list converter
     */
    @Autowired
    public BidListApiController(final BidListService bidListService,
                                final BidListConverter bidListConverter) {
        this.service = bidListService;
        this.converter = bidListConverter;
    }

    /**
     * save a new BidList.
     * @param bidListDTO bidList dto to save
     * @return message that indicates a successful operation
     */
    @PostMapping("/add")
    public String saveBidList(@RequestBody final BidListDTO bidListDTO) {
        LOGGER.debug("POST request sent from the saveBidList"
                + " of the BidListApiController");
        service.save(converter.bidListDTOToBidListEntity(bidListDTO));
        return "BidList saved successfully";
    }

    /**
     * Update bid list string.
     *
     * @param id         the id
     * @param bidListDTO the bid list dto
     * @return the string
     */
    @PutMapping("/update/{id}")
    public String updateBidList(@PathVariable final Integer id,
                                @RequestBody final BidListDTO bidListDTO) {
        LOGGER.debug("PUT request sent from the updateBidList"
                + " of the BidListApiController to update BidList {}", id);
        service.updateBidList(id,
                converter.bidListDTOToBidListEntity(bidListDTO));
        return "BidList updated successfully";
    }

    /**
     * Find bid list by id bid list dto.
     *
     * @param id the id
     * @return the bid list dto
     */
    @GetMapping("/findById/{id}")
    public Optional<BidListDTO> findBidListById(
            @PathVariable final Integer id) {
        LOGGER.debug("GET request sent from the findBidListById"
                + " of the BidListApiController to load BidList {}", id);
        return service.findBidListById(id);
    }

    /**
     * Retrieve all BidLists.
     * @return a list of BidListDTOs
     */
    @GetMapping("/findAll")
    public List<BidListDTO> findAllBidLists() {
        LOGGER.debug("GET request sent from the findAllBidLists"
                + " method of the BidListApiController"
                + " to retrieve all BidLists");
        return service.findAll();
    }

    /**
     * Delete BidList by id.
     * @param id id of the BidList to delete
     * @return a message that indicates a successful operation
     */
    @DeleteMapping("/delete/{id}")
    public String deleteBidListById(@PathVariable final Integer id) {
        service.deleteById(id);
        return "BidList deleted successfully";
    }
}
