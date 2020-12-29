package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import com.nnk.poseidon.security.passwordvalidation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
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
import javax.validation.constraints.NotBlank;

/**
 * This class groups al user related information.
 *
 * @author Yahia CHERIFI
 */
@Entity
@Table(name = "user")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class User {

    /**
     * user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    /**
     * user name.
     */
    @NonNull
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String username;

    /**
     * user password.
     */
    @NonNull
    @ValidPassword
    @NotBlank(message = "Password is mandatory and must not be black")
    @Column(name = "password",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String password;

    /**
     * user full name.
     */
    @NonNull
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "full_name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String fullName;

    /**
     * user role.
     */
    @NonNull
    @NotBlank(message = "Role is mandatory")
    @Column(name = "role",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String role;
}

