package com.nnk.poseidon.domain;

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
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * This class groups curvePoint related information.
 *
 * @author Yahia CHERIFI
 */

@Entity
@Table(name = "curve_point")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class CurvePoint {

    /**
     * CurvePoint id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curve_point_id")
    private Integer curvePointId;

    /**
     * curve id.
     */
    @NotNull(message = "Curve id is mandatory")
    @Column(name = "curve_id")
    private Integer curveId;

    /**
     * as of date.
     */
    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    /**
     * term.
     */
    @NotNull(message = "Term is mandatory")
    @Column(name = "term")
    private Double term;

    /**
     * value.
     */
    @NotNull(message = "Value id is mandatory")
    @Column(name = "value")
    private Double value;

    /**
     * creation date.
     */
    @Column(name = "creation_date")
    private Timestamp creationDate;

    /**
     * Class constructor.
     * @param cCurveId curveId
     * @param cAsOfDate asOfDate
     * @param cTerm term
     * @param cValue value
     * @param cCreationDate creationDate
     */
    public CurvePoint(@NotNull(message = "Curve id is mandatory")
                      final Integer cCurveId,
                      final Timestamp cAsOfDate,
                      @NotNull(message = "Term is mandatory")
                      final Double cTerm,
                      @NotNull(message = "Value id is mandatory")
                      final Double cValue,
                      final Timestamp cCreationDate) {
        this.curveId = cCurveId;
        this.asOfDate = new Timestamp(cAsOfDate.getTime());
        this.term = cTerm;
        this.value = cValue;
        this.creationDate = new Timestamp(cCreationDate.getTime());
    }

    /**
     * asOfDate getter.
     * @return getAsOf date
     */
    public Timestamp getAsOfDate() {
        if (asOfDate == null) {
            return null;
        } else {
            return new Timestamp(asOfDate.getTime());
        }
    }

    /**
     * asOfDate setter.
     * @param cAsOfDate date
     */
    public void setAsOfDate(final Timestamp cAsOfDate) {
        if (cAsOfDate == null) {
            this.creationDate = null;
        } else {
            this.asOfDate = new Timestamp(cAsOfDate.getTime());
        }
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
     * @param cCreationDate date
     */
    public void setCreationDate(final Timestamp cCreationDate) {
        this.creationDate = new Timestamp(cCreationDate.getTime());
    }
}
