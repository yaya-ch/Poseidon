package com.nnk.poseidon.unit.converters;

import com.nnk.poseidon.converters.CurvePointConverterImpl;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.unit.DataLoader;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yahia CHERIFI
 */

@Tag("Converters")
@DisplayName("CurvePointConverter unit tests")
class CurvePointConverterImplTest {

    private CurvePointConverterImpl curvePointConverter;

    private CurvePoint curvePoint;
    private CurvePointDTO curvePointDTO;
    @BeforeEach
    void setUp() {
        curvePointConverter = new CurvePointConverterImpl(new ModelMapper());
        DataLoader dataLoader = new DataLoader();
        curvePoint = dataLoader.setCurvePoint();
        curvePointDTO = dataLoader.setCurvePointDTO();
    }

    @DisplayName("Convert CurvePointDTO to CurvePoint entity")
    @Test
    void curvePointDTOToCurvePointEntity_shouldConvertCurvePointDTOTOCurvePointEntity() {
        CurvePoint result = curvePointConverter.curvePointDTOToCurvePointEntity(curvePointDTO);
        assertNotNull(result);
        assertEquals(result.getCurveId(), curvePointDTO.getCurveId());
        assertEquals(result.getAsOfDate(), curvePointDTO.getAsOfDate());
        assertEquals(result.getTerm(), curvePointDTO.getTerm());
        assertEquals(result.getValue(), curvePointDTO.getValue());
        assertEquals(result.getCreationDate(), curvePointDTO.getCreationDate());
    }

    @DisplayName("Convert a list of CurvePointDTO to a list of CurvePoint entities")
    @Test
    void curvePointDTOsToCurvePointEntities_shouldConvertCurvePointDTOListTOCurvePointEntityList() {
        List<CurvePointDTO> curvePointDTOList = new ArrayList<>();
        curvePointDTOList.add(curvePointDTO);
        List<CurvePoint> curvePointList = curvePointConverter.curvePointDTOsToCurvePointEntities(curvePointDTOList);
        assertEquals(curvePointList.size(), curvePointDTOList.size());
        assertEquals(curvePointList.get(0).getCurveId(), curvePointDTOList.get(0).getCurveId());
    }

    @DisplayName("Convert CurvePoint entity to CurvePointDTO")
    @Test
    void curvePointEntityToCurvePointDTO_shouldConvertCurvePointEntityTOCurvePointDTO() {
        CurvePointDTO result = curvePointConverter.curvePointEntityToCurvePointDTO(curvePoint);
        assertEquals(result.getCurveId(), curvePoint.getCurveId());
        assertEquals(result.getAsOfDate(), curvePoint.getAsOfDate());
        assertEquals(result.getTerm(), curvePoint.getTerm());
        assertEquals(result.getValue(), curvePoint.getValue());
        assertEquals(result.getCreationDate(), curvePoint.getCreationDate());
    }

    @DisplayName("Convert a list of CurvePoint entities to a list of CurvePointDTO")
    @Test
    void curvePointEntitiesToCurvePointDTOs_shouldConvertCurvePointEntityListTOCurvePointDTOList() {
        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);

        List<CurvePointDTO> result = curvePointConverter.curvePointEntitiesToCurvePointDTOs(curvePointList);
        assertEquals(result.size(), curvePointList.size());
        assertEquals(result.get(0).getCreationDate(), curvePointList.get(0).getCreationDate());
    }
}