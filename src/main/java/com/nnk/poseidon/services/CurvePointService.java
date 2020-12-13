package com.nnk.poseidon.services;

import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;

import java.util.List;
import java.util.Optional;

/**
 * The interface Curve point service.
 *
 * @author Yahia CHERIFI
 */

public interface CurvePointService {

    /**
     * Find CurvePoint by id.
     *
     * @param id the CurvePoint id
     * @return the CurvePoint if found
     */
    Optional<CurvePointDTO> findCurvePointById(Integer id);

    /**
     * Find all CurvePoints.
     *
     * @return a list of CurvePointDTOs
     */
    List<CurvePointDTO> findAllCurvePoints();

    /**
     * Save a new CurvePoint.
     *
     * @param curvePoint the CurvePoint to save
     * @return a call to the repo layer
     */
    CurvePoint saveCurvePoint(CurvePoint curvePoint);

    /**
     * Update an existing CurvePoint.
     *
     * @param id         the id of the CurvePoint to update
     * @param curvePoint the new CurvePoint
     * @return a call to the repo layer
     */
    CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint);

    /**
     * Delete CurvePoint by id.
     *
     * @param id the id of the CurvePoint to delete
     */
    void deleteCurvePointById(Integer id);
}
