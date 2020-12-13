package com.nnk.poseidon.repositories;

import com.nnk.poseidon.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface permits interactions between.
 * the application and the rule_name table
 *
 * @author Yahia CHERIFI
 */

public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
