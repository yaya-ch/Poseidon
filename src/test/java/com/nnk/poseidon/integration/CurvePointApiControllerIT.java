package com.nnk.poseidon.integration;

import com.nnk.poseidon.controllers.api.CurvePointApiController;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.repositories.CurvePointRepository;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("IntegrationTests")
@DisplayName("CurvePoint api controller integration tests")
@AutoConfigureWebMvc
@SpringBootTest
@Sql(scripts = {"/sqlScriptsForITs/scriptForIT.sql"})
class CurvePointApiControllerIT {

    @Autowired
    private CurvePointApiController controller;

    @Autowired
    private CurvePointRepository repository;

    private CurvePointDTO curvePointDTO;

    private Integer invalidId = 11;

    @BeforeEach
    void setUp() {
        DataLoader dataLoader = new DataLoader();
        curvePointDTO = dataLoader.setCurvePointDTO();
    }

    @DisplayName("POST: save CurvePoint successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenNewValidCurvePoint_whenSaveCurvePoint_thenResponseShouldBeOk() {
        controller.saveCurvePoint(curvePointDTO);
        List<CurvePoint> all = repository.findAll();
        assertEquals(2, all.size());
    }

    @DisplayName("POST: Invalid CurvePoint throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenNewInvalidCurvePoint_whenSaveCurvePoint_thenExceptionShouldBeThrown() {
        curvePointDTO.setValue(null);
        assertThrows(RuntimeException.class, () -> controller.saveCurvePoint(curvePointDTO));
    }

    @DisplayName("PUT: Update existing CurvePoint successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidCurvePoint_whenUpdateCurvePoint_thenResponseShouldBeOk() {
        Double newValue = 1234.0;
        curvePointDTO.setValue(newValue);
        controller.updateCurvePoint(1, curvePointDTO);
        Optional<CurvePoint> result = repository.findById(1);
        assertTrue(result.isPresent());
        assertEquals(newValue, result.get().getValue());
    }

    @DisplayName("PUT: Invalid CurvePoint throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidCurvePoint_whenUpdate_thenExceptionShouldBeThrown() {
        curvePointDTO.setValue(null);
        assertThrows(RuntimeException.class, () -> controller.updateCurvePoint(1, curvePointDTO));
    }

    @DisplayName("PUT: Providing invalid CurvePoint id throws exception")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidCurvePointId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThrows(RuntimeException.class, () -> controller.updateCurvePoint(invalidId, curvePointDTO));
    }

    @DisplayName("GET: Find all CurvePoints")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAll_shouldReturnAllCurvePoints() {
        List<CurvePointDTO> result = controller.findAll();
        assertEquals(1, result.size());
    }

    @DisplayName("DELETE CurvePoint Successfully")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenValidCurvePointId_whenDelete_thenCurvePointShouldBeDeleted() {
        controller.deleteCurvePointById(1);
        Optional<CurvePoint> result = repository.findById(1);
        assertFalse(result.isPresent());
    }

    @DisplayName("DELETE CurvePoint throws exception when CurvePoint id is not valid")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void givenInvalidCurvePointId_whenDelete_thenExceptionShouldBeThrown() {
        assertThrows(RuntimeException.class, () -> controller.deleteCurvePointById(invalidId));
    }
}
