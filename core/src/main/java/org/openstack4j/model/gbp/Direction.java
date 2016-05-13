package org.openstack4j.model.gbp;


import com.fasterxml.jackson.annotation.JsonCreator;

public enum Direction { 
    in,
    out,
    bi,
    UNRECOGNIZED;
    
    @JsonCreator
    public static Direction forValue(String value) {
        if (value != null)
        {
            for (Direction s : Direction.values()) {
                if (s.name().equalsIgnoreCase(value))
                    return s;
            }
        }
        return Direction.UNRECOGNIZED;
    }
}
