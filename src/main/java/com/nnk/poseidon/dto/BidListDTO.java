package com.nnk.poseidon.dto;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * This class groups information related to bid list.
 *
 * @author Yahia CHERIFI
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class BidListDTO {

    /**
     * BidList id.
     */
    private Integer bidListId;

    /**
     * account.
     */
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * type.
     */
    @NotNull
    @NotBlank(message = "Type is mandatory")
    private String type;

    /**
     * bid quantity.
     */
    @NotNull(message = "Bid quantity is mandatory and must be a number")
    private Double bidQuantity;

    /**
     * ask quantity.
     */
    private Double askQuantity;

    /**
     * bid.
     */
    private Double bid;

    /**
     * ask.
     */
    private Double ask;

    /**
     * benchmark.
     */
    private String benchmark;

    /**
     * bid list date.
     */
    private Timestamp bidListDate;

    /**
     * commentary.
     */
    private String commentary;

    /**
     * security.
     */
    private String security;

    /**
     * status.
     */
    private String status;

    /**
     * trader.
     */
    private String trader;

    /**
     * book.
     */
    private String book;

    /**
     * creation name.
     */
    private String creationName;

    /**
     * creation date.
     */
    private Timestamp creationDate;

    /**
     * revision name.
     */
    private String revisionName;

    /**
     * revision date.
     */
    private Timestamp revisionDate;

    /**
     * deal name.
     */
    private String dealName;

    /**
     * deal type.
     */
    private String dealType;

    /**
     * source list id.
     */
    private String sourceListId;

    /**
     * side.
     */
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
