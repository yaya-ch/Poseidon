package com.nnk.poseidon.unit.services;

import com.nnk.poseidon.converters.CurvePointConverter;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.repositories.CurvePointRepository;
import com.nnk.poseidon.services.CurvePointServiceImpl;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Services")
@DisplayName("CurvePointService unit tests")
class CurvePointServiceImplTest {

    private CurvePointServiceImpl service;

    @Mock
    private CurvePointRepository repository;

    @Mock
    private CurvePointConverter converter;

    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new CurvePointServiceImpl(repository, converter);
        DataLoader dataLoader = new DataLoader();
        curvePoint = dataLoader.setCurvePoint();
    }

    @DisplayName("Find curvePoint by id")
    @Test
    void givenValidCurvePointId_whenFindCurvePointByIdIsCalled_thenCorrectCurveShouldBeReturned() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        Optional<CurvePointDTO> expected = service.findCurvePointById(1);
        assertNotNull(expected);
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("Find curvePoint by id throws exception")
    @Test
    void givenInvalidCurvePointId_whenFindCurvePointByIdIsCalled_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.findCurvePointById(1));
    }

    @DisplayName("Retrieve all CurvePoints")
    @Test
    void findAllCurvePoints_shouldReturnCurvePointList() {
        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);
        when(repository.findAll()).thenReturn(curvePointList);
        service.findAllCurvePoints();
        verify(repository, times(1)).findAll();

    }

    @DisplayName("Save CurvePoint successfully")
    @Test
    void givenValidCurvePoint_whenSaveCurvePointIsCalled_thenCurvePointShouldBeSaved() {
        when(repository.save(any(CurvePoint.class))).thenReturn(curvePoint);
        service.saveCurvePoint(curvePoint);
        verify(repository, times(1)).save(curvePoint);
    }

    @DisplayName("Update CurvePoint successfully")
    @Test
    void givenValidId_whenUpdateCurvePoint_thenCurvePointShouldBeUpdated() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        when(repository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint expected = service.updateCurvePoint(1, curvePoint);
        assertNotNull(expected);
        verify(repository, times(1)).save(curvePoint);
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("Update CurvePoint fails")
    @Test
    void givenInvalidId_whenUpdateCurvePoint_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.updateCurvePoint(1, curvePoint));
        verify(repository, never()).save(any(CurvePoint.class));
    }

    @DisplayName("Delete CurvePoint successfully")
    @Test
    void givenValidCurveId_whenDeleteCurvePointByIdIsCalled_thenCurvePointShouldBeDeleted() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        service.deleteCurvePointById(1);
        service.deleteCurvePointById(1);
        verify(repository, times(2)).deleteById(1);
    }

    @DisplayName("Delete CurvePoint fails")
    @Test
    void givenInvalidCurveId_whenDeleteCurvePointByIdIsCalled_thenExceptionShouldBeThrown() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.deleteCurvePointById(1));
        verify(repository, never()).deleteById(1);
    }
}