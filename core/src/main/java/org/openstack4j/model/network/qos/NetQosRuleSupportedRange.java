package org.openstack4j.model.network.qos;

import java.util.Map;

import org.openstack4j.model.ModelEntity;

/**
 * A Network (Neutron) QoS Rule Driver Supported for Range
 *
 * @author Guoshuai Li
 */
public interface NetQosRuleSupportedRange extends ModelEntity, NetQosRuleSupported {

    /**
     * @return The valid values for parameter values.
     */
    Map<String, Integer> getParameterValues();

    /**
     * @return The valid values for parameter type.
     */
    String getParameterType();
}
