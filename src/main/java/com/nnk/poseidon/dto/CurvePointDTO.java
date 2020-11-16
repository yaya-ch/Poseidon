package com.nnk.poseidon.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * This class groups information related to curve point.
 *
 * @author Yahia CHERIFI
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CurvePointDTO {

    /**
     * CurvePoint id.
     */
    private Integer curvePointId;

    /**
     * curve id.
     */
    @NotNull(message = "Curve id is mandatory")
    private Integer curveId;

    /**
     * as of date.
     */
    private Timestamp asOfDate;

    /**
     * term.
     */
    @NotNull(message = "Term is mandatory")
    private Double term;

    /**
     * value.
     */
    @NotNull(message = "Value id is mandatory")
    private Double value;

    /**
     * creation date.
     */
    private Timestamp creationDate;

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
