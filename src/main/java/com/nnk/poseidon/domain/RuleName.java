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
 * This class groups all RuleNAme related information.
 *
 * @author Yahia CHERIFI
 */

@Entity
@Table(name = "rule_name")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
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
    @Column(name = "name",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String name;

    /**
     * description.
     */
    @Column(name = "description",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String description;

    /**
     * json.
     */
    @Column(name = "json",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String json;

    /**
     * template.
     */
    @Column(name = "template",
            length = ConstantNumbers.FIVE_HUNDREDS_AND_TWELVE)
    private String template;

    /**
     * sqlStr.
     */
    @Column(name = "sql_str",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sqlStr;

    /**
     * sqlPart.
     */
    @Column(name = "sql_part",
            length = ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE)
    private String sqlPart;

    /**
     * Class constructor.
     * @param rName name
     * @param rDescription description
     * @param rJson json
     * @param rTemplate template
     * @param rSqlStr sqlStr
     * @param rSqlPart sqlPart
     */
    public RuleName(final String rName,
                    final String rDescription,
                    final String rJson,
                    final String rTemplate,
                    final String rSqlStr,
                    final String rSqlPart) {
        this.name = rName;
        this.description = rDescription;
        this.json = rJson;
        this.template = rTemplate;
        this.sqlStr = rSqlStr;
        this.sqlPart = rSqlPart;
    }
}
