package org.openstack4j.model.dns.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by coa.ke on 6/17/17.
 */
public enum RecordSetType {
    A, AAAA, MX, CNAME, TXT, NS, SOA;
    @JsonValue
    public String value() {
        return name().toUpperCase();
    }

    @JsonCreator
    public static RecordSetType value(String v)
    {
        if (v == null) return null;
        try {
            return valueOf(v.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
