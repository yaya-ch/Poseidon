package com.nnk.poseidon.services;

import com.nnk.poseidon.converters.BidListConverter;
import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.repositories.BidListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements BidListService.
 *
 * @author Yahia CHERIFI
 * @see BidListService
 */

@Service
public class BidListServiceImpl implements BidListService {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(BidListServiceImpl.class);

    /**
     * Static string for the error message.
     */
    private static final String ERROR_MESSAGE = "No matching element found";

    /**
     * BidListRepository to inject.
     */
    private final BidListRepository repository;

    /**
     * BidListConverter to inject.
     */
    private final BidListConverter converter;

    /**
     * Constructor injection.
     * @param bidListRepository BidListRepository
     * @param bidListConverter BidListConverter
     */
    @Autowired
    public BidListServiceImpl(final BidListRepository bidListRepository,
                              final BidListConverter bidListConverter) {
        this.repository = bidListRepository;
        this.converter = bidListConverter;
    }

    /**
     * Save a new BidList.
     *
     * @param bidList the BidList
     * @return the BidList
     */
    @Transactional
    @Override
    public BidList save(final BidList bidList) {
        Optional<BidList> checkForDuplicateBidList =
                repository.findByAccountAndType(bidList.getAccount(),
                        bidList.getType());
        if (checkForDuplicateBidList.isPresent()) {
            LOGGER.error("Failed to save a new BidList."
                            + " BidList {} matches the provided one",
                    bidList.getBidListId());
            throw new NoSuchElementException("Failed to save the new BidList");
        } else {
            LOGGER.info("BidList saved successfully: {}",
                    bidList.getBidListId());
            return repository.save(bidList);
        }
    }

    /**
     * Update an existing BidList.
     *
     * @param id         the id of the BidList to update
     * @param bidList the BidList new information
     */
    @Transactional
    @Override
    public BidList updateBidList(final Integer id, final BidList bidList) {
        Optional<BidList> checkForBidList =
                repository.findById(id);
        if (!checkForBidList.isPresent()) {
            LOGGER.error("Failed to load BidList {}."
                    + " No matching element found", id);
            throw new NoSuchElementException("Failed to update BidList."
                    + ERROR_MESSAGE);
        } else {
            bidList.setBidListId(checkForBidList.get().getBidListId());
            LOGGER.info("BidList {} updated successfully", id);
            return repository.save(bidList);
        }
    }

    /**
     * Find BidList by id.
     *
     * @param id the id
     * @return the BidList
     */
    @Override
    public BidListDTO findBidListById(final Integer id) {
        Optional<BidList> checkForExistingBidList = repository.findById(id);
        if (!checkForExistingBidList.isPresent()) {
            LOGGER.error("Failed to load BidList {}."
                    + " No matching Item found", id);
            throw new NoSuchElementException("Failed to load BidList."
                    + ERROR_MESSAGE);
        } else {
            LOGGER.info("BidList {} loaded successfully.", id);
            return converter
                    .bidListEntityToBidListDTO(checkForExistingBidList.get());
        }
    }

    /**
     * Retrieve all BidLists.
     * @return a list of BidListDTOs
     */
    @Override
    public List<BidListDTO> findAll() {
        List<BidList> findAllBidLists = repository.findAll();
        return converter.bidListEntitiesToBidListDTOs(findAllBidLists);
    }

    /**
     * Delete a BidList by id.
     *
     * @param id the id of the BidList to delete
     */
    @Override
    public void deleteById(final Integer id) {
        Optional<BidList> checkForBidList = repository.findById(id);
        if (!checkForBidList.isPresent()) {
            LOGGER.error("Failed to delete BidList {}."
                    + ERROR_MESSAGE, id);
            throw new NoSuchElementException("No matching found");
        } else {
            LOGGER.info("BidList {} deleted successfully", id);
            repository.deleteById(id);
        }
    }
}
