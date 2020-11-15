package com.nnk.poseidon.integration;

import com.nnk.poseidon.controllers.api.BidListApiController;
import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.repositories.BidListRepository;
import com.nnk.poseidon.unit.DataLoaderForUnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("IntegrationTests")
@DisplayName("BidList api controller integration tests")
@AutoConfigureWebMvc
@SpringBootTest
@Sql(scripts = {"/sqlScriptsForITs/scriptForIT.sql"})
public class BidListApiControllerIT {

    @Autowired
    private BidListApiController apiController;

    @Autowired
    private BidListRepository repository;

    private BidListDTO bidListDTO;

    private final Integer validBidListId = 1;
    private final Integer invalidBidListId = 666;

    @BeforeEach
    void setUp() {
        DataLoaderForUnitTests dataLoaderForUnitTests = new DataLoaderForUnitTests();
        bidListDTO = dataLoaderForUnitTests.setBidListDTO();
    }

    @DisplayName("Find all BidLists")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void findAll_shouldRetrieveAllBidListsFromDataBase() {
        List<BidListDTO> retrieveAll = apiController.findAllBidLists();
        assertEquals(1, retrieveAll.size());
    }

    @DisplayName("FindById returns the correct BidList")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenExistingId_whenFindById_thenCorrectBidListShouldBeReturnedFromDB() {

        Optional<BidListDTO> bidListToRetrieve = apiController.findBidListById(validBidListId);
        assertTrue(bidListToRetrieve.isPresent());
        assertEquals("Account", bidListToRetrieve.get().getAccount());
    }

    @DisplayName("FindById throws exception when no id matches")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenNonExistingId_whenFindById_thenExceptionShouldBeThrown() {
        assertThrows(RuntimeException.class, () -> apiController.findBidListById(invalidBidListId));
    }

    @DisplayName("Save BidList successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenValidBidList_whenSaveBidListIsCalled_thenBidListShouldBeSaved() {
        apiController.saveBidList(bidListDTO);
        List<BidListDTO> findAll = apiController.findAllBidLists();
        assertEquals(2, findAll.size());
    }

    @DisplayName("Saving invalid BidList throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void giveInvalidBidList_whenSaveBidListIsCalled_thenExceptionShouldBeThrown() {
        bidListDTO.setAccount("");
        assertThrows(ConstraintViolationException.class, () -> apiController.saveBidList(bidListDTO));
    }

    @DisplayName("Update BidList successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenValidBidList_whenUpdateBidListIsCalled_thenBidListShouldBeUpdated() {
        bidListDTO.setAccount("This is the new account");
        apiController.updateBidList(validBidListId, bidListDTO);
        Optional<BidList> updatedBidList = repository.findById(1);
        assertTrue(updatedBidList.isPresent());
        assertNotNull(updatedBidList.get().getRevisionDate());
        assertEquals("This is the new account", updatedBidList.get().getAccount());
    }

    @DisplayName("Updating invalid BidList throws an exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenInvalidBidList_whenUpdateBidListIsCalled_thenExceptionShouldBeThrown() {
        bidListDTO.setAccount("");
        assertThrows(RuntimeException.class, () -> apiController.updateBidList(validBidListId, bidListDTO));
    }

    @DisplayName("Delete a BidList successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenCorrectBidListId_whenDeleteById_thenBidListShouldBeDeleted() {
        apiController.deleteBidListById(validBidListId);
        Optional<BidList> deletedBidList = repository.findById(validBidListId);
        assertFalse(deletedBidList.isPresent());
    }

    @DisplayName("Delete BidList throws an exception when id is not valid")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void givenInvalidBidListId_whenDeleteById_thenExceptionShouldBeThrown() {
        assertThrows(RuntimeException.class, () -> apiController.updateBidList(invalidBidListId, bidListDTO));
    }
}
