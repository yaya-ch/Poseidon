package com.nnk.poseidon.repositories;

import com.nnk.poseidon.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface allows interactions.
 * between the app and the curve_point table
 *
 * @author Yahia CHERIFI
 */

public interface CurvePointRepository
        extends JpaRepository<CurvePoint, Integer> {
}
