package com.nnk.poseidon.converters;

import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.CurvePointDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements CurvePointConverter.
 *
 * @author Yahia CHERIFI
 * @see CurvePointConverter
 */

@Component
public class CurvePointConverterImpl implements CurvePointConverter {

    /**
     * ModelMapper to inject.
     */
    private final ModelMapper mapper;


    /**
     * Instantiates a new Curve point converter.
     *
     * @param modelMapper the model mapper
     */
    @Autowired
    public CurvePointConverterImpl(final ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    /**
     * Converts from CurvePointDTO to CurvePoint entity.
     *
     * @param curvePointDTO the CurvePointDTO to convert
     * @return a curvePoint entity
     */
    @Override
    public CurvePoint curvePointDTOToCurvePointEntity(
            final CurvePointDTO curvePointDTO) {
        return mapper.map(curvePointDTO, CurvePoint.class);
    }

    /**
     * Converts a list of CurvePointDTO to a list of CurvePoints.
     *
     * @param curvePointDTOS the list of CurvePointDTO to convert
     * @return a list of CurvePoint entities
     */
    @Override
    public List<CurvePoint> curvePointDTOsToCurvePointEntities(
            final List<CurvePointDTO> curvePointDTOS) {
        return curvePointDTOS.stream()
                .map(this::curvePointDTOToCurvePointEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts from CurvePoint to CurvePointDTO.
     *
     * @param curvePoint the CurvePoint to convert
     * @return a CurvePointDTO
     */
    @Override
    public CurvePointDTO curvePointEntityToCurvePointDTO(
            final CurvePoint curvePoint) {
        return mapper.map(curvePoint, CurvePointDTO.class);
    }

    /**
     * Converts a list of CurvePoint entities to a list of CurvePointDTOs.
     *
     * @param curvePoints the CurvePoint list to convert
     * @return a list of CurvePointDTO
     */
    @Override
    public List<CurvePointDTO> curvePointEntitiesToCurvePointDTOs(
            final List<CurvePoint> curvePoints) {
        return curvePoints.stream()
                .map(this::curvePointEntityToCurvePointDTO)
                .collect(Collectors.toList());
    }
}
