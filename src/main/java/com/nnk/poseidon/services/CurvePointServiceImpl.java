package com.nnk.poseidon.services;

import com.nnk.poseidon.converters.CurvePointConverter;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Curve point service.
 *
 * @author Yahia CHERIFI This class implements the CurvePointService
 * @see CurvePointService
 */
@Service
public class CurvePointServiceImpl implements CurvePointService {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CurvePointServiceImpl.class);

    /**
     * Error message.
     */
    private static final String ERROR_MESSAGE =
            " No matching element is present";

    /**
     * CurvePointRepository to inject.
     */
    private final CurvePointRepository repository;

    /**
     * CurvePointConverter to inject.
     */
    private final CurvePointConverter converter;

    /**
     * Instantiates a new CurvePointService.
     *
     * @param curveRepository the curve repository
     * @param pointConverter  the point converter
     */
    @Autowired
    public CurvePointServiceImpl(final CurvePointRepository curveRepository,
                                 final CurvePointConverter pointConverter) {
        this.repository = curveRepository;
        this.converter = pointConverter;
    }

    /**
     * Find CurvePoint by id.
     *
     * @param id the CurvePoint id
     * @return the CurvePoint if found
     */
    @Override
    public Optional<CurvePointDTO> findCurvePointById(final Integer id) {
        Optional<CurvePoint> findCurvePoint = repository.findById(id);
        if (findCurvePoint.isPresent()) {
            LOGGER.info("CurvePoint {} retrieved successfully", id);
            return Optional.ofNullable(converter
                    .curvePointEntityToCurvePointDTO(findCurvePoint.get()));
        } else {
            LOGGER.error("Failed to retrieve CurvePoint {} from database."
                    + ERROR_MESSAGE, id);
            throw new NoSuchElementException("Failed to retrieve"
                    + " the specified curvePoint");
        }
    }

    /**
     * Find all CurvePoints.
     *
     * @return a list of CurvePointDTOs
     */
    @Override
    public List<CurvePointDTO> findAllCurvePoints() {
        List<CurvePoint> retrieveAll = repository.findAll();
        return converter.curvePointEntitiesToCurvePointDTOs(retrieveAll);
    }

    /**
     * Save a new CurvePoint.
     *
     * @param curvePoint the CurvePoint to save
     * @return a call to the repo layer
     */
    @Transactional
    @Override
    public CurvePoint saveCurvePoint(final CurvePoint curvePoint) {
        return repository.save(curvePoint);
    }

    /**
     * Update an existing CurvePoint.
     *
     * @param id         the id of the CurvePoint to update
     * @param curvePoint the new CurvePoint
     * @return a call to the repo layer
     */
    @Transactional
    @Override
    public CurvePoint updateCurvePoint(final Integer id,
                                       final CurvePoint curvePoint) {
        Optional<CurvePoint> checkIfExist = repository.findById(id);
        if (checkIfExist.isPresent()) {
            LOGGER.info("CurvePoint {} updated successfully", id);
            curvePoint.setCurvePointId(checkIfExist.get().getCurvePointId());
            curvePoint.setCreationDate(checkIfExist.get().getCreationDate());
            return repository.save(curvePoint);
        } else {
            LOGGER.error("Failed to update CurvePont {}."
                    + ERROR_MESSAGE, id);
            throw new NoSuchElementException("Failed to retrieve CurvePoint");
        }
    }

    /**
     * Delete CurvePoint by id.
     *
     * @param id the id of the CurvePoint to delete
     */
    @Override
    public void deleteCurvePointById(final Integer id) {
        Optional<CurvePoint> checkIfExists = repository.findById(id);
        if (checkIfExists.isPresent()) {
            LOGGER.info("CurvePoint {} deleted successfully", id);
            repository.deleteById(id);
        } else {
            LOGGER.error("Failed to delete CurvePoint {}."
                    + ERROR_MESSAGE, id);
            throw new NoSuchElementException("Failed to delete the wanted"
                    + " CurvePoint. No matching id found in database");
        }
    }
}
