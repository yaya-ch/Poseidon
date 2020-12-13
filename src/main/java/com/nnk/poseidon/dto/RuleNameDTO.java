package com.nnk.poseidon.dto;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

/**
 * This class groups information related to RuleName.
 *
 * @author Yahia CHERIFI
 */

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RuleNameDTO {

    /**
     * RuleName id.
     */
    private Integer id;

    /**
     * name.
     */
    @NonNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * description.
     */
    @NonNull
    @NotBlank(message = "Description is mandatory")
    private String description;

    /**
     * json.
     */
    @NonNull
    @NotBlank(message = "Json is mandatory")
    private String json;

    /**
     * template.
     */
    @NonNull
    @NotBlank(message = "Template is mandatory")
    private String template;

    /**
     * sqlStr.
     */
    @NonNull
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;

    /**
     * sqlPart.
     */
    @NonNull
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;
}
