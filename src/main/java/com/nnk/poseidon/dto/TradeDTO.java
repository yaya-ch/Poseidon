package com.nnk.poseidon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class TradeDTO {

    /**
     * trade id.
     */
    private Integer tradeId;

    /**
     * account.
     */
    @NotNull
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * type.
     */
    @NotNull
    @NotBlank(message = "Type is mandatory")
    private String type;

    /**
     * buy quantity.
     */
    private Double buyQuantity;

    /**
     * sell quantity.
     */
    private Double sellQuantity;

    /**
     * buy price.
     */
    private Double buyPrice;

    /**
     * sell price.
     */
    private Double sellPrice;

    /**
     * benchmark.
     */
    private String benchmark;

    /**
     * trade date.
     */
    private Timestamp tradeDate;

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
     * tradeDate getter.
     * @return trade date
     */
    public Timestamp getTradeDate() {
        if (tradeDate == null) {
            return null;
        } else {
            return new Timestamp(tradeDate.getTime());
        }
    }

    /**
     * tradeDate setter.
     * @param tTradeDate date
     */
    public void setTradeDate(final Timestamp tTradeDate) {
        this.tradeDate = new Timestamp(tTradeDate.getTime());
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
     * @param tCreationDate date
     */
    public void setCreationDate(final Timestamp tCreationDate) {
        this.creationDate = new Timestamp(tCreationDate.getTime());
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
     * @param tRevisionDate date
     */
    public void setRevisionDate(final Timestamp tRevisionDate) {
        if (tRevisionDate == null) {
            this.revisionDate = null;
        } else {
            this.revisionDate = new Timestamp(tRevisionDate.getTime());
        }
    }
}
