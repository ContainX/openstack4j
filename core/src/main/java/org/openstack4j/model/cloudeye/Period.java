package org.openstack4j.model.cloudeye;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by coa.ke on 6/24/17.
 */
public enum Period {
    REAL_TIME(1, "real time"), FIVE_MINS(300, "five minutes"), TWENTY_MINS(1200,
            "twenty minutes"), ONE_HOUR(3600, "one hour"), FOUR_HOURS(14400, "four hours"), ONE_DAY(
            86400, "one day");
    private final int code;
    private final String message;

    private Period(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage(String message) {
        return message + "\t-->\t" + this.message;
    }

    public String toString() {
        return this.code + "(" + this.message + ")";
    }

}
