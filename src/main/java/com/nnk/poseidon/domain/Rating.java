package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.AllArgsConstructor;
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

/**
 * This class groups rating related information.
 *
 *  @author Yahia CHERIFI
 */

@Entity
@Table(name = "rating")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Rating {

    /**
     * Rating id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer id;

    /**
     * moody's rating.
     */
    @Column(name = "moodys_rating",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String moodysRating;

    /**
     * sand p rating.
     */
    @Column(name = "sand_p_rating",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sandPRating;

    /**
     * fitch rating.
     */
    @Column(name = "fitch_rating",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String fitchRating;

    /**
     * order number.
     */
    @Column(name = "order_number")
    private Integer orderNumber;

    /**
     * Class constructor.
     * @param mRating moodysRating
     * @param sRating sandPRating
     * @param fRating fitchRating
     * @param orderNum orderNumber
     */
    public Rating(final String mRating,
                  final String sRating,
                  final String fRating,
                  final Integer orderNum) {
        this.moodysRating = mRating;
        this.sandPRating = sRating;
        this.fitchRating = fRating;
        this.orderNumber = orderNum;
    }
}
