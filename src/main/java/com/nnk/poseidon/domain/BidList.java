package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NonNull;

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
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
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
    @NonNull
    @NotNull
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", length = ConstantNumbers.THIRTY)
    private String account;

    /**
     * type.
     */
    @NonNull
    @NotNull
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", length = ConstantNumbers.THIRTY)
    private String type;

    /**
     * bid quantity.
     */
    @NonNull
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
