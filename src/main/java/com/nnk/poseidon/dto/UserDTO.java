package com.nnk.poseidon.dto;

import com.nnk.poseidon.security.passwordvalidation.ValidPassword;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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
    @ValidPassword
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
