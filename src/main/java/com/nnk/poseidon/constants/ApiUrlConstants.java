package com.nnk.poseidon.constants;

/**
 * This class contains different constants.
 * in which the Poseidon base urls are defined
 *
 * @author Yahia CHERIFI
 */

public final class ApiUrlConstants {

    /**
     * Class constructor.
     */
    protected ApiUrlConstants() {
    }

    /**
     * A constant String that refers to the BidList api base url.
     */
    public static final String BID_LIST_API_BASE_URL =
            "http://localhost:8080/api/bidList";

    /**
     * A constant String that refers to the CurvePoint api base url.
     */
    public static final String CURVE_POINT_API_BASE_URL =
            "http://localhost:8080/api/curvePoint";

    /**
     * A constant String that refers to the Rating api base url.
     */
    public static final String RATING_API_BASE_URL =
            "http://localhost:8080/api/rating";

    /**
     * A constant String that refers to the RuleName api base url.
     */
    public static final String RULE_NAME_API_BASE_URL =
            "http://localhost:8080/api/ruleName";

    /**
     * A constant String that refers to the RuleName api base url.
     */
    public static final String TRADE_API_BASE_URL =
            "http://localhost:8080/api/trade";
}
