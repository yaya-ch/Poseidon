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
import javax.validation.constraints.NotBlank;

/**
 * This class groups al user related information.
 *
 * @author Yahia CHERIFI
 */
@Entity
@Table(name = "user")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class User {

    /**
     * user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    /**
     * user name.
     */
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String username;

    /**
     * user password.
     */
    @NotBlank(message = "Password is mandatory")
    @Column(name = "password",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String password;

    /**
     * user full name.
     */
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "full_name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String fullName;

    /**
     * user role.
     */
    @NotBlank(message = "Role is mandatory")
    @Column(name = "role",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String role;
}

