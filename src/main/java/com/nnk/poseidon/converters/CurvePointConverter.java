package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;

import java.util.List;

/**
 * The interface Curve point converter.
 * It allows conversion from/to CurvePoint/CurvePointDTO
 *
 * @author Yahia CHERIFI
 */

public interface CurvePointConverter {

    /**
     * Converts from CurvePointDTO to CurvePoint entity.
     *
     * @param curvePointDTO the CurvePointDTO to convert
     * @return a curvePoint entity
     */
    CurvePoint curvePointDTOToCurvePointEntity(CurvePointDTO curvePointDTO);

    /**
     * Converts a list of CurvePointDTO to a list of CurvePoints.
     *
     * @param curvePointDTOS the list of CurvePointDTO to convert
     * @return a list of CurvePoint entities
     */
    List<CurvePoint> curvePointDTOsToCurvePointEntities(
            List<CurvePointDTO> curvePointDTOS);

    /**
     * Converts from CurvePoint to CurvePointDTO.
     *
     * @param curvePoint the CurvePoint to convert
     * @return a CurvePointDTO
     */
    CurvePointDTO curvePointEntityToCurvePointDTO(CurvePoint curvePoint);

    /**
     * Converts a list of CurvePoint entities to a list of CurvePointDTOs.
     *
     * @param curvePoints the CurvePoint list to convert
     * @return a list of CurvePointDTO
     */
    List<CurvePointDTO> curvePointEntitiesToCurvePointDTOs(
            List<CurvePoint> curvePoints);
}
