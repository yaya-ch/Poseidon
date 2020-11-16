package com.nnk.poseidon.controllers.api;


import com.nnk.poseidon.converters.CurvePointConverter;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.services.CurvePointService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * This is an API REST controller that allows CRUD operations on CurvePoint.
 *
 * @author Yahia CHERIFI
 */

@RestController
@RequestMapping("/api/curvePoint")
public class CurvePointApiController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CurvePointApiController.class);

    /**
     * CurvePointService to inject.
     */
    private final CurvePointService service;

    /**
     * CurvePointConverter.
     */
    private final CurvePointConverter converter;

    /**
     * Instantiates a new CurvePointApiController.
     *
     * @param curveService   the CurvePointService
     * @param curveConverter the CurvePointConverter
     */
    @Autowired
    public CurvePointApiController(final CurvePointService curveService,
                                   final CurvePointConverter curveConverter) {
        this.service = curveService;
        this.converter = curveConverter;
    }

    /**
     * Save CurvePoint.
     *
     * @param curvePoint the CurvePointDTO to save
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Save a new CurvePoint")
    @PostMapping("/add")
    public String saveCurvePoint(@RequestBody
                                 @Valid final CurvePointDTO curvePoint) {
        LOGGER.debug("POST request set from the saveCurvePoint"
                + " of the CurvePointApiController to save a new CurvePoint");
        curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        CurvePoint curvePointToSave = converter
                .curvePointDTOToCurvePointEntity(curvePoint);
        service.saveCurvePoint(curvePointToSave);
        return "CurvePoint saved successfully";
    }

    /**
     * Update CurvePoint.
     *
     * @param id            the CurvePoint id
     * @param curvePoint    CurvePointDTO that holds the new information
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Update an existing CurvePoint")
    @PutMapping("/update/{id}")
    public String updateCurvePoint(@PathVariable final Integer id,
                                   @RequestBody
                                   @Valid final CurvePointDTO curvePoint) {
        LOGGER.debug("PUT request sent from the updateCurvePoint of the"
                + " CurvePointApiController to update CurvePoint {}", id);
        service.updateCurvePoint(id,
                converter.curvePointDTOToCurvePointEntity(curvePoint));
        return "CurvePoint updated successfully";
    }

    /**
     * Find all list.
     *
     * @return a list CurvePointDTO
     */
    @ApiOperation(value = "Retrieve all CurvePoints from database")
    @GetMapping("/findAll")
    public List<CurvePointDTO> findAll() {
        LOGGER.debug("GET request sent from the findAll of the"
                + " CurvePointApiController to retrieve all CurvePoints");
        return service.findAllCurvePoints();
    }

    /**
     * Find CurvePoint by id.
     *
     * @param id the id of the CurvePoint
     * @return CurvePointDTO if found
     */
    @ApiOperation(value = "Retrieve CurvePoint from database by Id")
    @GetMapping("/findById/{id}")
    public Optional<CurvePointDTO> findCurvePointById(
            @PathVariable final Integer id) {
        LOGGER.debug("GET request sent from the findCurvePointById of the"
                + " CurvePointApiController to retrieve CurvePoint {}", id);
        return service.findCurvePointById(id);
    }

    /**
     * Delete CurvePoint by id.
     *
     * @param id the id of the CurvePoint to delete
     * @return a message that indicates a successful operation
     */
    @ApiOperation(value = "Delete a CurvePoint from database by Id")
    @DeleteMapping("/delete/{id}")
    public String deleteCurvePointById(@PathVariable final Integer id) {
        LOGGER.debug("DELETE request sent from the deleteCurvePointById of the"
                + " CurvePointApiController to delete curvePoint {}", id);
        service.deleteCurvePointById(id);
        return "CurvePoint deleted successfully";
    }
}
