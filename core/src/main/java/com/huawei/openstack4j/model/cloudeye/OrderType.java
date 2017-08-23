package com.huawei.openstack4j.model.cloudeye;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.dns.v2.ZoneType;

/**
 * Created by coa.ke on 6/24/17.
 */
public enum OrderType {
    DESC, ASC;

    @JsonValue
    public String value() {
        return name().toLowerCase();
    }

    //default to DESC
    @JsonCreator
    public static OrderType value(String v)
    {
        if (v == null) return DESC;
        try {
            return valueOf(v.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DESC;
        }
    }

}
