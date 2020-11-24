package com.nnk.poseidon.unit;

import com.nnk.poseidon.domain.BidList;
import com.nnk.poseidon.domain.CurvePoint;
import com.nnk.poseidon.domain.Rating;
import com.nnk.poseidon.domain.RuleName;
import com.nnk.poseidon.dto.BidListDTO;
import com.nnk.poseidon.dto.CurvePointDTO;
import com.nnk.poseidon.dto.RatingDTO;
import com.nnk.poseidon.dto.RuleNameDTO;

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

    /**
     * set a new Rating.
     * @return rating
     */
    public Rating setRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Moody's rating");
        rating.setSandPRating("S&P rating");
        rating.setFitchRating("Fitch rating");
        rating.setOrderNumber(1255);
        return rating;
    }

    /**
     * set a new RatingDTO.
     * @return ratingDTO
     */
    public RatingDTO setRatingDTO() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(1);
        ratingDTO.setMoodysRating("Moody's rating");
        ratingDTO.setSandPRating("S&P rating");
        ratingDTO.setFitchRating("Fitch rating");
        ratingDTO.setOrderNumber(1255);
        return ratingDTO;
    }

    /**
     * set a new RuleName.
     * @return ruleName
     */
    public RuleName setRuleName() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("rule name");
        ruleName.setDescription("rule name description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("sql str");
        ruleName.setSqlPart("sql part");
        return ruleName;
    }

    /**
     * set a new RuleNameDTO.
     * @return ruleNameDTO
     */
    public RuleNameDTO setRuleNameDTO() {
        RuleNameDTO ruleNameDTO = new RuleNameDTO();
        ruleNameDTO.setId(1);
        ruleNameDTO.setName("DTO rule name");
        ruleNameDTO.setDescription("DTO rule name description");
        ruleNameDTO.setJson("DTO json");
        ruleNameDTO.setTemplate("DTO template");
        ruleNameDTO.setSqlStr("DTO sql str");
        ruleNameDTO.setSqlPart("DTO sql part");
        return ruleNameDTO;
    }
}
