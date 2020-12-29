package com.nnk.poseidon.dto;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class groups information related to Rating.
 *
 * @author Yahia CHERIFI
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class RatingDTO {

    /**
     * Rating id.
     */
    private Integer id;

    /**
     * moody's rating.
     */
    @NotBlank(message = "Moody's Rating is mandatory")
    private String moodysRating;

    /**
     * s and p rating.
     */
    @NotBlank(message = "S & P Rating is mandatory")
    private String sandPRating;

    /**
     * fitch rating.
     */
    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;

    /**
     * order number.
     */
    @NotNull(message = "Order number is mandatory")
    private Integer orderNumber;
}
