package org.openstack4j.model.gbp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Protocol {
    tcp,
    udp,
    icmp,
    http,
    https,
    smpt,
    dns,
    ftp,
    None,  
    UNRECOGNIZED;
    
    @JsonCreator
    public static Protocol forValue(String value) {
        if (value != null) 
        {
            for (Protocol s : Protocol.values()) {
                if (s.name().equalsIgnoreCase(value))
                    return s;
            }
        }
        return Protocol.UNRECOGNIZED;
    }
    
    @JsonValue
    public String value() {
        return name().toLowerCase();
    }
}
