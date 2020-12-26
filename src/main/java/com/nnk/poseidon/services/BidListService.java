package com.nnk.poseidon.services;

import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;

import java.util.List;

/**
 * The interface BidList service.
 * It contains abstract methods that provide
 * the logic to operated on the data sent to and from
 * the controllers and the repository layer
 *
 * @author Yahia CHERIFI
 */
public interface BidListService {

    /**
     * Save a new BidList.
     *
     * @param bidList the BidList
     * @return the BidList
     */
    BidList save(BidList bidList);

    /**
     * Update an existing BidList.
     *
     * @param id      the id of the BidList to update
     * @param bidList the BidList new information
     * @return a call to the repository layer
     */
    BidList updateBidList(Integer id, BidList bidList);

    /**
     * Find BidList by id.
     *
     * @param id the id
     * @return the BidList
     */
    BidListDTO findBidListById(Integer id);

    /**
     * Delete a BidList by id.
     *
     * @param id the id of the BidList to delete
     */
    void deleteById(Integer id);

    /**
     * Retrieve all BidLists.
     * @return a list of BidListDTOs
     */
    List<BidListDTO> findAll();
}
