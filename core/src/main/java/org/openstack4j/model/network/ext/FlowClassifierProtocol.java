package org.openstack4j.model.network.ext;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A protocol type used within Flow Classifier
 */
public enum FlowClassifierProtocol {
    TCP,
    UDP;

    @JsonCreator
    public static FlowClassifierProtocol forValue(String value) {
        if (value != null)
        {
            for (FlowClassifierProtocol s : FlowClassifierProtocol.values()) {
                if (s.name().equalsIgnoreCase(value)) {
                    return s;
                }
            }
        }
        return FlowClassifierProtocol.TCP;
    }
}
