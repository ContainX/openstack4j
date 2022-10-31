package org.openstack4j.api.networking.qos;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosPolicy;

/**
 * Provides Neutron QoS Policy services.
 *
 * @author Guoshuai Li
 */
public interface NetQosPolicyService extends RestService {

    /**
     * Returns list of Neutron QoS Policy.
     *
     * @return List of Neutron QoS Policy or empty
     */
    List<? extends NetQosPolicy> list();

    /**
     * Returns list of Neutron QoS Policy filtered by parameters.
     *
     * @param filteringParams map (name, value) of filtering parameters
     * @return List of Neutron QoS Policy or empty
     */
    List<? extends NetQosPolicy> list(Map<String, String> filteringParams);

    /**
     * Gets a Neutron QoS Policy by id.
     *
     * @param id the Neutron QoS Policy identifier
     * @return the Neutron QoS Policy
     */
    NetQosPolicy get(String id);

    /**
     * Deletes Neutron QoS Policy by id.
     *
     * @param id the Neutron QoS Policy identifier
     * @return the action response
     */
    ActionResponse delete(String id);

    /**
     * Creates a new Neutron QoS Policy
     *
     * @param qosPolicy the Neutron QoS Policy
     * @return the Neutron QoS Policy
     */
    NetQosPolicy create(NetQosPolicy qosPolicy);

    /**
     * Updates a Neutron QoS Policy
     *
     * @param id the Neutron QoS Policy identifier
     * @param qosPolicy the Neutron QoS Policy to update
     * @return the updated Neutron QoS Policy
     */
    NetQosPolicy update(String id, NetQosPolicy qosPolicy);
}
