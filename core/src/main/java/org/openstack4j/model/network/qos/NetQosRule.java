package org.openstack4j.model.network.qos;

import org.openstack4j.model.ModelEntity;

/**
 * A Network (Neutron) QoS Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosRule extends ModelEntity {

    /**
     * @return The QoS Policy ID.
     */
    String getQosPolicy();
}
