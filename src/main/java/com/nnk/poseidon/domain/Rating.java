package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.NonNull;
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

/**
 * This class groups rating related information.
 *
 *  @author Yahia CHERIFI
 */

@Entity
@Table(name = "rating")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
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
    @NonNull
    @NotBlank(message = "Moody's Rating is mandatory")
    @Column(name = "moodys_rating",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String moodysRating;

    /**
     * s and p rating.
     */
    @NonNull
    @NotBlank(message = "S & P rating is mandatory")
    @Column(name = "sand_p_rating",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sandPRating;

    /**
     * fitch rating.
     */
    @NonNull
    @NotBlank(message = "Fitch Rating is mandatory")
    @Column(name = "fitch_rating",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String fitchRating;

    /**
     * order number.
     */
    @NonNull
    @NotNull
    @Column(name = "order_number")
    private Integer orderNumber;
}
