package com.nnk.poseidon.unit;

import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.dto.CurvePointDTO;

import java.sql.Timestamp;

/**
 * This class loads data for unit tests.
 *
 * @author Yahia CHERIFI
 */

public class DataLoader {

    /**
     * set a new BidList.
     * @return bidList
     */
    public BidList setBidList() {
        BidList bidList = new BidList();
        bidList.setType("type");
        bidList.setAccount("account");
        bidList.setBidQuantity(22.0);
        bidList.setAskQuantity(22.0);
        bidList.setBid(1.1);
        bidList.setAsk(1.1);
        bidList.setBenchmark("benchmark");
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary("commentary");
        bidList.setSecurity("security");
        bidList.setStatus("status");
        bidList.setTrader("trade");
        bidList.setBook("book");
        bidList.setCreationName("creation name");
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList.setRevisionName("revision name");
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName("deal name");
        bidList.setDealType("deal type");
        bidList.setSourceListId("2");
        bidList.setSide("side");
        return bidList;
    }

    /**
     * set a new bidListDTO.
     * @return bidListDTO
     */
    public BidListDTO setBidListDTO() {
        BidListDTO bidListDTO = new BidListDTO();
        bidListDTO.setType("DTO type");
        bidListDTO.setAccount("DTO account");
        bidListDTO.setBidQuantity(100.0);
        bidListDTO.setAskQuantity(100.0);
        bidListDTO.setBid(10.0);
        bidListDTO.setAsk(10.0);
        bidListDTO.setBenchmark("DTO benchmark");
        bidListDTO.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidListDTO.setCommentary("DTO commentary");
        bidListDTO.setSecurity("DTO security");
        bidListDTO.setStatus("DTO status");
        bidListDTO.setTrader("DTO trade");
        bidListDTO.setBook("DTO book");
        bidListDTO.setCreationName("DTO creation name");
        bidListDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidListDTO.setRevisionName("DTO revision name");
        bidListDTO.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidListDTO.setDealName("DTO deal name");
        bidListDTO.setDealType("DTO deal type");
        bidListDTO.setSourceListId("2");
        bidListDTO.setSide("DTO side");
        return bidListDTO;
    }

    /**
     * set a new CurvePoint.
     * @return curvePoint
     */
    public CurvePoint setCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));
        curvePoint.setTerm(100.0);
        curvePoint.setValue(50.0);
        curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return curvePoint;
    }

    /**
     * set a new CurvePointDTO.
     * @return curvePointDTO
     */
    public CurvePointDTO setCurvePointDTO() {
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        curvePointDTO.setCurveId(2);
        curvePointDTO.setAsOfDate(new Timestamp(System.currentTimeMillis()));
        curvePointDTO.setTerm(200.0);
        curvePointDTO.setValue(100.0);
        curvePointDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return curvePointDTO;
    }
}
