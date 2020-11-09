package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Bid list converter.
 *
 * @author Yahia CHERIFI
 * @see BidListConverter
 */

@Component
public class BidListConverterImpl implements BidListConverter {

    private final ModelMapper mapper;

    /**
     * Instantiates a new Bid list converter.
     *
     * @param modelMapper the model mapper
     */
    @Autowired
    public BidListConverterImpl(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * Converts a BidListDTO to a BidList entity.
     *
     * @param bidListDTO bidListDTO to convert
     * @return BidList entity
     */
    @Override
    public BidList bidListDTOToBidListEntity(BidListDTO bidListDTO) {
        return mapper.map(bidListDTO, BidList.class);
    }

    /**
     * Converts a list of BidListDTO to a list of BidList entities.
     *
     * @param bidListDTOList a list to convert
     * @return a list of BidList entities
     */
    @Override
    public List<BidList> bidListDTOsToBidListEntities(List<BidListDTO> bidListDTOList) {
        return bidListDTOList.stream()
                .map(this::bidListDTOToBidListEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts a BidList entity to a BidListDTO.
     *
     * @param bidList bidList to convert
     * @return a bidListDTO
     */
    @Override
    public BidListDTO bidListEntityToBidListDTO(BidList bidList) {
        return mapper.map(bidList, BidListDTO.class);
    }

    /**
     * Converts a list of BidList entities to a list of BidListDTOs.
     *
     * @param bidListList a list to convert
     * @return a list of BidListDTOs
     */
    @Override
    public List<BidListDTO> bidListEntitiesToBidListDTOs(List<BidList> bidListList) {
        return bidListList.stream()
                .map(this::bidListEntityToBidListDTO)
                .collect(Collectors.toList());
    }
}
