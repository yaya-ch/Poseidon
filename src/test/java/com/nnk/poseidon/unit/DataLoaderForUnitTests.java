package com.nnk.poseidon.unit;

import com.nnk.poseidon.domain.BidList;

import java.sql.Timestamp;

/**
 * This class loads data for unit tests.
 *
 * @author Yahia CHERIFI
 */

public class DataLoaderForUnitTests {

    private BidList bidList;
    public BidList setBidList() {
        bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setType("type");
        bidList.setAccount("account");
        bidList.setBidQuantity(22.0);
        bidList.setAskQuantity(22.0);
        bidList.setBid(1.1);
        bidList.setAsk(1.1);
        bidList.setBenchmark("benc");
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary("com");
        bidList.setSecurity("sec");
        bidList.setStatus("sta");
        bidList.setTrader("tra");
        bidList.setBook("book");
        bidList.setCreationName("crea name");
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList.setRevisionName("rev name");
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName("deal name");
        bidList.setDealType("d type");
        bidList.setSourceListId("2");
        bidList.setSide("side");
        return bidList;
    }
}
