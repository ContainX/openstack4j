package org.openstack4j.api.networking.qos;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosMinimumBandwidthRule;

/**
 * Provides Neutron QoS Minimum Bandwidth Rule services.
 *
 * @author Guoshuai Li
 */
public interface NetQosMinimumBandwidthRuleService extends RestService {

    /**
     * Returns list of Neutron QoS Minimum Bandwidth Rules.
     *
     * @return List of Neutron QoS Minimum Bandwidth Rules or empty
     */
    List<? extends NetQosMinimumBandwidthRule> list(String qosPolicyId);

    /**
     * Gets a Neutron QoS Minimum Bandwidth Rule by id.
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS Minimum Bandwidth Rule identifier
     * @return the Neutron QoS Minimum Bandwidth Rule
     */
    NetQosMinimumBandwidthRule get(String qosPolicyId, String ruleId);

    /**
     * Deletes Neutron QoS Minimum Bandwidth Rule by id.
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS Minimum Bandwidth Rule identifier
     * @return the action response
     */
    ActionResponse delete(String qosPolicyId, String ruleId);

    /**
     * Creates a new Neutron QoS Minimum Bandwidth Rule
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param qosMinimumBandwidthRule the Neutron QoS Minimum Bandwidth Rule
     * @return the Neutron QoS Minimum Bandwidth Rule
     */
    NetQosMinimumBandwidthRule create(String qosPolicyId, NetQosMinimumBandwidthRule qosMinimumBandwidthRule);

    /**
     * Updates a Neutron QoS Minimum Bandwidth Rule
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS Minimum Bandwidth Rule identifier
     * @param qosMinimumBandwidthRule the Neutron QoS Minimum Bandwidth Rule
     * @return the updated Neutron QoS Minimum Bandwidth Rule
     */
    NetQosMinimumBandwidthRule update(String qosPolicyId, String ruleId, NetQosMinimumBandwidthRule qosMinimumBandwidthRule);
}
