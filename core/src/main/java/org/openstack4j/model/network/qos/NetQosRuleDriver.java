package org.openstack4j.model.network.qos;

import java.util.List;

import org.openstack4j.model.ModelEntity;

/**
 * A Network (Neutron) QoS Rule Driver
 *
 * @author Guoshuai Li
 */
public interface NetQosRuleDriver extends ModelEntity {

    /**
     * @return Field name contains the name of a backend driver.
     */
    String getName();

    /**
     * @return Field supported_parameters contains a list of dicts with NetQosRuleSupported fields.
     */
    List<? extends NetQosRuleSupported> getSupportedParameters();
}
