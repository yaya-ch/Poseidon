package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Converters")
@DisplayName("BidList converter")
class BidListConverterImplTest {

    private BidListConverter bidListConverter;

    private BidList bidList;

    private BidListDTO bidListDTO;

    private ModelMapper mapper;

    private void setNewBidList() {
        bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(10.0);
        bidList.setAskQuantity(10.0);
        bidList.setBid(10.0);
        bidList.setAsk(10.0);
        bidList.setBenchmark("benchmark");
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary("commentary");
        bidList.setSecurity("security");
        bidList.setStatus("status");
        bidList.setTrader("trader");
        bidList.setBook("book");
        bidList.setCreationName("creation name");
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList.setRevisionName("revision name");
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName("deal name");
        bidList.setDealType("deal type");
        bidList.setSourceListId("1");
        bidList.setSide("side");
    }

    private void setNewBidListDTO() {
        bidListDTO = new BidListDTO();
        bidListDTO.setAccount("account DTO");
        bidListDTO.setType("type DTO");
        bidListDTO.setBidQuantity(100.0);
        bidListDTO.setAskQuantity(100.0);
        bidListDTO.setBid(100.0);
        bidListDTO.setAsk(100.0);
        bidListDTO.setBenchmark("benchmark DTO");
        bidListDTO.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidListDTO.setCommentary("commentary DTO");
        bidListDTO.setSecurity("security DTO");
        bidListDTO.setStatus("status DTO");
        bidListDTO.setTrader("trader DTO");
        bidListDTO.setBook("book DTO");
        bidListDTO.setCreationName("creation name DTO");
        bidListDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidListDTO.setRevisionName("revision name DTO");
        bidListDTO.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidListDTO.setDealName("deal name DTO");
        bidListDTO.setDealType("deal type DTO");
        bidListDTO.setSourceListId("11");
        bidListDTO.setSide("side DTO");
    }

    @BeforeEach
    void setUp() {
        mapper = new ModelMapper();
        bidListConverter = new BidListConverterImpl(mapper);
        setNewBidList();
        setNewBidListDTO();
    }

    @AfterEach
    void tearDown() {
        mapper = null;
        bidListConverter = null;
        bidList = null;
        bidListDTO = null;
    }

    @DisplayName("BidListDto to BidList conversion")
    @Test
    void givenBidListDTO_whenConverterCalled_thenItShouldBeConvertedToBidListEntity() {
        BidList result = bidListConverter.bidListDTOToBidListEntity(bidListDTO);

        assertEquals(result.getAccount(), bidListDTO.getAccount());
        assertEquals(result.getType(), bidListDTO.getType());
        assertEquals(result.getBid(), bidListDTO.getBid());
    }

    @DisplayName("BidListDto list to BidList list conversion")
    @Test
    void givenListOfBidListDTOs_whenConverterCalled_thenItShouldBeConvertedToBidListEntityList() {
        List<BidListDTO> dtoList = new ArrayList<>();
        dtoList.add(bidListDTO);

        List<BidList> result = bidListConverter.bidListDTOsToBidListEntities(dtoList);

        assertEquals(result.size(), dtoList.size());
    }

    @DisplayName("BidList to BidListDTO conversion")
    @Test
    void givenBidListEntity_whenConverterCalled_thenItShouldBeConvertedToBidListDTO() {
        BidListDTO result = bidListConverter.bidListEntityToBidListDTO(bidList);

        assertEquals(result.getType(), bidList.getType());
        assertEquals(result.getBidListDate(), bidList.getBidListDate());
        assertEquals(result.getBidQuantity(), bidList.getBidQuantity());
    }

    @DisplayName("BidList list to BidListDTO list conversion")
    @Test
    void givenListOfBidListEntities_whenConverterCalled_thenItShouldBeConvertedToBidListDTOList() {
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(bidList);

        List<BidListDTO> result = bidListConverter.bidListEntitiesToBidListDTOs(bidLists);

        assertEquals(result.size(), bidLists.size());
    }
}