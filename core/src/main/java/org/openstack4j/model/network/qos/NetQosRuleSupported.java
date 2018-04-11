package org.openstack4j.model.network.qos;

import org.openstack4j.model.ModelEntity;

/**
 * A Network (Neutron) QoS Rule Driver Supported
 *
 * @author Guoshuai Li
 */
public interface NetQosRuleSupported extends ModelEntity {

    /**
     * @return The valid values for parameter_name.
     */
    String getParameterName();
}
