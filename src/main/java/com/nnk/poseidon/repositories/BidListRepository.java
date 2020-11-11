package com.nnk.poseidon.repositories;

import com.nnk.poseidon.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * This interface allows interactions between the app and the bid_list table.
 *
 * @author Yahia CHERIFI
 */

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    /**
     * Find BidLst by account and type.
     * @param account BidList account
     * @param type BidList type
     * @return the bidList if found
     */
    Optional<BidList> findByAccountAndType(String account, String type);
}
