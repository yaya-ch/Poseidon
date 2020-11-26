package com.nnk.poseidon.unit;

import com.nnk.poseidon.domain.*;
import com.nnk.poseidon.dto.*;

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

    /**
     * Set a new Trade.
     * @return trade instance
     */
    public Trade setTrade() {
        Trade trade = new Trade();
        trade.setTradeId(22);
        trade.setAccount("trade account");
        trade.setType("Trade type");
        trade.setBuyQuantity(23.0);
        trade.setSellQuantity(12.0);
        trade.setSellPrice(10.0);
        trade.setBenchmark("Trade benchmark");
        trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
        trade.setSecurity("Trade is secure");
        trade.setStatus("Trade status");
        trade.setTrader("Trader");
        trade.setBook("Trade book");
        trade.setCreationName("Trade creation name");
        trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
        trade.setRevisionName("Trade revision name");
        trade.setRevisionDate(new Timestamp((System.currentTimeMillis())));
        trade.setDealName("Trade deal name");
        trade.setDealType("Trade deal type");
        trade.setSourceListId("Source List ID");
        trade.setSide("Trade Side");
        return trade;
    }

    /**
     * Set a new TradeDTO
     * @return tradeDTO instance
     */
    public TradeDTO setTradeDTO() {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setTradeId(33);
        tradeDTO.setAccount("TradeDTO account");
        tradeDTO.setType("TradeDTO type");
        tradeDTO.setBuyQuantity(29.0);
        tradeDTO.setSellQuantity(19.0);
        tradeDTO.setSellPrice(15.0);
        tradeDTO.setBenchmark("TradeDTO benchmark");
        tradeDTO.setTradeDate(new Timestamp(System.currentTimeMillis()));
        tradeDTO.setSecurity("TradeDTO is secure");
        tradeDTO.setStatus("TradeDTO status");
        tradeDTO.setTrader("TradeDTO Trader");
        tradeDTO.setBook("TradeDTO book");
        tradeDTO.setCreationName("TradeDTO creation name");
        tradeDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
        tradeDTO.setRevisionName("TradeDTO revision name");
        tradeDTO.setRevisionDate(new Timestamp((System.currentTimeMillis())));
        tradeDTO.setDealName("TradeDTO deal name");
        tradeDTO.setDealType("TradeDTO deal type");
        tradeDTO.setSourceListId("TradDTO Source List ID");
        tradeDTO.setSide("TradeDTO Side");
        return tradeDTO;
    }
}
