package com.nnk.poseidon.dto;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This class groups User related information.
 *
 * @author Yahia CHERIFI
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UserDTO {

    /**
     * user id.
     */
    private Integer id;

    /**
     * user name.
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * user password.
     */
    @Size(min = ConstantNumbers.EIGHT,
            max = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE,
            message = "Password must have at least"
            + " 8 characters and at most 125 characters")
    @NotBlank(message = "Password is mandatory and must not be black")
    private String password;

    /**
     * user full name.
     */
    @NotBlank(message = "FullName is mandatory")
    private String fullName;

    /**
     * user role.
     */
    @NotBlank(message = "Role is mandatory")
    private String role;
}
