package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * This class groups information related to bidList.
 *
 * @author Yahia CHERIFI
 */

@Entity
@Table(name = "bid_list")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class BidList {

    /**
     * BidList id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    /**
     * account.
     */
    @NotNull
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", length = ConstantNumbers.THIRTY)
    private String account;

    /**
     * type.
     */
    @NotNull
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", length = ConstantNumbers.THIRTY)
    private String type;

    /**
     * bid quantity.
     */
    @NotNull(message = "Bid quantity is mandatory and must be a number")
    @Column(name = "bid_quantity")
    private Double bidQuantity;

    /**
     * ask quantity.
     */
    @Column(name = "ask_quantity")
    private Double askQuantity;

    /**
     * bid.
     */
    @Column(name = "bid")
    private Double bid;

    /**
     * ask.
     */
    @Column(name = "ask")
    private Double ask;

    /**
     * benchmark.
     */
    @Column(name = "benchmark",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String benchmark;

    /**
     * bid list date.
     */
    @Column(name = "bid_list_date")
    private Timestamp bidListDate;

    /**
     * commentary.
     */
    @Column(name = "commentary",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String commentary;

    /**
     * security.
     */
    @Column(name = "security",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String security;

    /**
     * status.
     */
    @Column(name = "status",
            length = ConstantNumbers.TEN)
    private String status;

    /**
     * trader.
     */
    @Column(name = "trader",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String trader;

    /**
     * book.
     */
    @Column(name = "book",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String book;

    /**
     * creation name.
     */
    @Column(name = "creation_name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String creationName;

    /**
     * creation date.
     */
    @Column(name = "creation_date")
    private Timestamp creationDate;

    /**
     * revision name.
     */
    @Column(name = "revision_name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String revisionName;

    /**
     * revision date.
     */
    @Column(name = "revision_date")
    private Timestamp revisionDate;

    /**
     * deal name.
     */
    @Column(name = "deal_name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String dealName;

    /**
     * deal type.
     */
    @Column(name = "deal_type",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String dealType;

    /**
     * source list id.
     */
    @Column(name = "source_list_id",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sourceListId;

    /**
     * side.
     */
    @Column(name = "side",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String side;

    /**
     * Class constructor.
     * @param bAccount account
     * @param bType type
     * @param bQuantity bidQuantity
     * @param aQuantity askQuantity
     * @param bBid bid
     * @param bAsk ask
     * @param bBenchmark benchmark
     * @param bListDate bidListDate
     * @param bCommentary commentary
     * @param bSecurity security
     * @param bStatus status
     * @param bTrader trader
     * @param bBook book
     * @param bCreationName creationName
     * @param bCreationDate creationDate
     * @param bRevisionName revisionName
     * @param bRevisionDate revisionDate
     * @param bDealName dealName
     * @param bDealType dealType
     * @param bSourceListId sourceListId
     * @param bSide side
     */
    public BidList(final @NotNull @NotBlank String bAccount,
                   final @NotNull @NotBlank String bType,
                   final @NotNull Double bQuantity,
                   final Double aQuantity,
                   final Double bBid,
                   final Double bAsk,
                   final String bBenchmark,
                   final Timestamp bListDate,
                   final String bCommentary,
                   final String bSecurity,
                   final String bStatus,
                   final String bTrader,
                   final String bBook,
                   final String bCreationName,
                   final Timestamp bCreationDate,
                   final String bRevisionName,
                   final Timestamp bRevisionDate,
                   final String bDealName,
                   final String bDealType,
                   final String bSourceListId, final String bSide) {
        this.account = bAccount;
        this.type = bType;
        this.bidQuantity = bQuantity;
        this.askQuantity = aQuantity;
        this.bid = bBid;
        this.ask = bAsk;
        this.benchmark = bBenchmark;
        this.bidListDate = new Timestamp(bListDate.getTime());
        this.commentary = bCommentary;
        this.security = bSecurity;
        this.status = bStatus;
        this.trader = bTrader;
        this.book = bBook;
        this.creationName = bCreationName;
        this.creationDate = new Timestamp(bCreationDate.getTime());
        this.revisionName = bRevisionName;
        this.revisionDate = new Timestamp(bRevisionDate.getTime());
        this.dealName = bDealName;
        this.dealType = bDealType;
        this.sourceListId = bSourceListId;
        this.side = bSide;
    }

    /**
     * Class constructor.
     * @param bAccount account
     * @param bType type
     * @param bQuantity bid quantity
     */
    public BidList(@NotNull @NotBlank final String bAccount,
                   @NotNull @NotBlank final String bType,
                   @NotNull @NotBlank final Double bQuantity) {
        this.account = bAccount;
        this.type = bType;
        this.bidQuantity = bQuantity;
    }

    /**
     *  bidListDate getter.
     * @return bidList date
     */
    public Timestamp getBidListDate() {
        if (bidListDate == null) {
            return null;
        } else {
            return new Timestamp(bidListDate.getTime());
        }
    }

    /**
     * bidListDate setter.
     * @param bListDate date
     */
    public void setBidListDate(final Timestamp bListDate) {
        this.bidListDate = new Timestamp(bListDate.getTime());
    }

    /**
     * creationDate getter.
     * @return creation date
     */
    public Timestamp getCreationDate() {
        if (creationDate == null) {
            return null;
        } else {
            return new Timestamp(creationDate.getTime());
        }
    }

    /**
     * creationDate setter.
     * @param bCreationDate creation date
     */
    public void setCreationDate(final Timestamp bCreationDate) {
        this.creationDate = new Timestamp(bCreationDate.getTime());
    }

    /**
     * revisionDate getter.
     * @return revision date
     */
    public Timestamp getRevisionDate() {
        if (revisionDate == null) {
            return null;
        } else {
            return new Timestamp(revisionDate.getTime());
        }
    }

    /**
     * revisionDate setter.
     * @param bRevisionDate date
     */
    public void setRevisionDate(final Timestamp bRevisionDate) {
        if (bRevisionDate == null) {
            this.revisionDate = null;
        } else {
            this.revisionDate = new Timestamp(bRevisionDate.getTime());
        }
    }
}
