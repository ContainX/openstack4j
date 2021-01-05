package org.openstack4j.model.network.qos;

import org.openstack4j.model.ModelEntity;

public interface NetQosRuleType extends ModelEntity {

    /**
     * @return The type of QoS rule.
     */
    String getType();
}
