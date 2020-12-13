package com.nnk.poseidon.domain;

import com.nnk.poseidon.constants.ConstantNumbers;
import lombok.AllArgsConstructor;
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

/**
 * This class groups all RuleName related information.
 *
 * @author Yahia CHERIFI
 */

@Entity
@Table(name = "rule_name")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class RuleName {

    /**
     * RuleName id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_name_id")
    private Integer id;

    /**
     * name.
     */
    @NonNull
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String name;

    /**
     * description.
     */
    @NonNull
    @NotBlank(message = "Description is mandatory")
    @Column(name = "description",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String description;

    /**
     * json.
     */
    @NonNull
    @NotBlank(message = "Json is mandatory")
    @Column(name = "json",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String json;

    /**
     * template.
     */
    @NonNull
    @NotBlank(message = "Template is mandatory")
    @Column(name = "template",
            length = ConstantNumbers.FIVE_HUNDREDS_AND_TWELVE)
    private String template;

    /**
     * sqlStr.
     */
    @NonNull
    @NotBlank(message = "SqlStr is mandatory")
    @Column(name = "sql_str",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sqlStr;

    /**
     * sqlPart.
     */
    @NonNull
    @NotBlank(message = "SqlPart is mandatory")
    @Column(name = "sql_part",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sqlPart;
}
