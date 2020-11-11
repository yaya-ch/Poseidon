package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;

import java.util.List;

/**
 * This interface provides some abstract methods that allow.
 * conversion from/to BidList entity/BidListDTO
 *
 * @author Yahia CHERIFI
 */

public interface BidListConverter {

    /**
     * Converts a BidListDTO to a BidList entity.
     *
     * @param bidListDTO bidListDTO to convert
     * @return BidList entity
     */
    BidList bidListDTOToBidListEntity(BidListDTO bidListDTO);

    /**
     * Converts a list of BidListDTO to a list of BidList entities.
     *
     * @param bidListDTOList a list to convert
     * @return a list of BidList entities
     */
    List<BidList> bidListDTOsToBidListEntities(List<BidListDTO> bidListDTOList);

    /**
     * Converts a BidList entity to a BidListDTO.
     *
     * @param bidList bidList to convert
     * @return a bidListDTO
     */
    BidListDTO bidListEntityToBidListDTO(BidList bidList);

    /**
     * Converts a list of BidList entities to a list of BidListDTOs.
     *
     * @param bidListList a list to convert
     * @return a list of BidListDTOs
     */
    List<BidListDTO> bidListEntitiesToBidListDTOs(List<BidList> bidListList);
}
