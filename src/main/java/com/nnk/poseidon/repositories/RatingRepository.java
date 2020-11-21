package com.nnk.poseidon.repositories;

import com.nnk.poseidon.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface allows interactions between the app and the rating table.
 *
 * @author Yahia CHERIFI
 */

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
