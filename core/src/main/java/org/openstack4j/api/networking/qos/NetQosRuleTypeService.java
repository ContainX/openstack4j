package org.openstack4j.api.networking.qos;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.network.qos.NetQosRuleDrivers;
import org.openstack4j.model.network.qos.NetQosRuleType;

/**
 * Provides Neutron QoS Rule Types services.
 *
 * @author Guoshuai Li
 */
public interface NetQosRuleTypeService extends RestService {

    /**
     * Returns list of Neutron QoS Rule Types.
     *
     * @return List of Neutron QoS Rule Types or empty
     */
    List<? extends NetQosRuleType> listRuleTypes();

    /**
     * Returns list of Neutron QoS Rule Type Drivers.
     *
     * @return List of Neutron QoS Rule Type Drivers or empty
     */
    NetQosRuleDrivers getRuleTypeDrivers(String qosRuleType);
}
