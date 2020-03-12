package org.openstack4j.model.octavia;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A protocol type used within Load Balancing
 */
public enum Protocol {
    HTTP,
    HTTPS,
    PROXY,
    TCP;

    @JsonCreator
    public static Protocol forValue(String value) {
        if (value != null)
        {
            for (Protocol s : Protocol.values()) {
                if (s.name().equalsIgnoreCase(value)) {
                    return s;
                }
            }
        }
        return Protocol.HTTP;
    }
}
