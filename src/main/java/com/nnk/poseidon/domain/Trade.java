package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
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
 * This class groups all trade related information.
 *
 * @author Yahia CHERIFI
 */

@Entity
@Table(name = "trade")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Trade {

    /**
     * trade id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Integer tradeId;

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
     * buy quantity.
     */
    @Column(name = "buy_quantity")
    private Double buyQuantity;

    /**
     * sell quantity.
     */
    @Column(name = "sell_quantity")
    private Double sellQuantity;

    /**
     * buy price.
     */
    @Column(name = "buy_price")
    private Double buyPrice;

    /**
     * sell price.
     */
    @Column(name = "sell_price")
    private Double sellPrice;

    /**
     * benchmark.
     */
    @Column(name = "benchmark",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String benchmark;

    /**
     * trade date.
     */
    @Column(name = "trade_date",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private Timestamp tradeDate;

    /**
     * security.
     */
    @Column(name = "security",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String security;

    /**
     * status.
     */
    @Column(name = "status", length = ConstantNumbers.TEN)
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
