package org.openstack4j.api.networking.qos;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosBandwidthLimitRule;

/**
 * Provides Neutron QoS Bandwidth Limit Rule services.
 *
 * @author Guoshuai Li
 */
public interface NetQosBandwidthLimitRuleService extends RestService {

    /**
     * Returns list of Neutron QoS Bandwidth Limit Rules.
     *
     * @return List of Neutron QoS Bandwidth Limit Rules or empty
     */
    List<? extends NetQosBandwidthLimitRule> list(String qosPolicyId);

    /**
     * Gets a Neutron QoS Bandwidth Limit Rule by id.
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS Bandwidth Limit Rule identifier
     * @return the Neutron QoS Bandwidth Limit Rule
     */
    NetQosBandwidthLimitRule get(String qosPolicyId, String ruleId);

    /**
     * Deletes Neutron QoS Bandwidth Limit Rule by id.
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS Bandwidth Limit Rule identifier
     * @return the action response
     */
    ActionResponse delete(String qosPolicyId, String ruleId);

    /**
     * Creates a new Neutron QoS Bandwidth Limit Rule
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param qosBandwidthLimitRule the Neutron QoS Bandwidth Limit Rule
     * @return the Neutron QoS Bandwidth Limit Rule
     */
    NetQosBandwidthLimitRule create(String qosPolicyId, NetQosBandwidthLimitRule qosBandwidthLimitRule);

    /**
     * Updates a Neutron QoS Bandwidth Limit Rule
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS Bandwidth Limit Rule identifier
     * @param qosBandwidthLimitRule the Neutron QoS Bandwidth Limit Rule
     * @return the updated Neutron QoS Bandwidth Limit Rule
     */
    NetQosBandwidthLimitRule update(String qosPolicyId, String ruleId, NetQosBandwidthLimitRule qosBandwidthLimitRule);
}
