package com.nnk.poseidon.unit.services;

import com.nnk.poseidon.unit.DataLoaderForUnitTests;
import com.nnk.poseidon.converters.BidListConverter;
import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.repositories.BidListRepository;
import com.nnk.poseidon.services.BidListServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BidListServiceImplTest {

    private BidListServiceImpl service;

    @Mock
    private BidListRepository repository;

    @Mock
    private BidListConverter converter;

    private DataLoaderForUnitTests dataLoaderForUnitTests;

    private BidList bidList;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new BidListServiceImpl(repository, converter);
        dataLoaderForUnitTests = new DataLoaderForUnitTests();
        bidList = dataLoaderForUnitTests.setBidList();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenNewBidList_whenSaveMethodIsCalled_thenBidListShouldBeSaved() {
        when(repository.save(any(BidList.class))).thenReturn(bidList);
        service.save(bidList);
        verify(repository, times(1)).save(bidList);
    }

    @Test
    void givenExistingBidList_whenSaveMethodIsCalled_thenExceptionShouldBeThrown() {
        BidList bidList1 = new BidList();
        bidList1.setType("type");
        bidList1.setAccount("account");
        when(repository.findByAccountAndType("account", "type")).thenReturn(java.util.Optional.of(bidList));
        assertThrows(RuntimeException.class, () -> service.save(bidList));
    }

    @Test
    void givenExistingBidListToUpdate_whenUpdateMethodIsCalled_thenBidListShouldBeUpdated() {
        when(repository.findById(anyInt())).thenReturn(java.util.Optional.of(bidList));
        when(repository.save(bidList)).thenReturn(bidList);

        BidList expected = service.updateBidList(1, bidList);
        assertNotNull(expected);
        verify(repository, times(1)).save(bidList);
        verify(repository, times(1)).findById(1);
    }

    @Test
    void givenNonExistingBidListToUpdate_whenUpdateMethodIsCalled_theExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.updateBidList(1, bidList));
    }

    @Test
    void givenCorrectId_whenFindByIdIsCalled_thenBidListShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(bidList));
        Optional<BidListDTO> expected = service.findBidListById(1);
        assertNotNull(expected);
    }

    @Test
    void givenIncorrectId_whenFidByIdISCalled_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.findBidListById(1));
    }

    @Test
    void findAll_shouldReturnAllBidLists() {
        List<BidList> bidListList = new ArrayList<>();
        bidListList.add(bidList);
        when(repository.findAll()).thenReturn(bidListList);
        List<BidListDTO> converted = converter.bidListEntitiesToBidListDTOs(bidListList);
        List<BidListDTO> expected = service.findAll();
        assertEquals(expected, converted);

    }

    @Test
    void givenCorrectId_WhenDeleteByIdIsCalled_thenBidListShouldBeDeleted() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(bidList));
        service.deleteById(1);
        service.deleteById(1);
        verify(repository, times(2)).deleteById(1);
    }

    @Test
    void givenIncorrectId_WhenDeleteByIdIsCalled_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.deleteById(1));
    }
}