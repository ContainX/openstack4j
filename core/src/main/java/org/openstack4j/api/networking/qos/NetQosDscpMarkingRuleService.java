package org.openstack4j.api.networking.qos;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.qos.NetQosDscpMarkingRule;

/**
 * Provides Neutron QoS DSCP Marking Rule services.
 *
 * @author Guoshuai Li
 */
public interface NetQosDscpMarkingRuleService extends RestService {

    /**
     * Returns list of Neutron QoS DSCP Marking Rules.
     *
     * @return List of Neutron QoS DSCP Marking Rules or empty
     */
    List<? extends NetQosDscpMarkingRule> list(String qosPolicyId);

    /**
     * Gets a Neutron QoS DSCP Marking Rule by id.
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS DSCP Marking Rule identifier
     * @return the Neutron QoS DSCP Marking Rule
     */
    NetQosDscpMarkingRule get(String qosPolicyId, String ruleId);

    /**
     * Deletes Neutron QoS DSCP Marking Rule by id.
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS DSCP Marking Rule identifier
     * @return the action response
     */
    ActionResponse delete(String qosPolicyId, String ruleId);

    /**
     * Creates a new Neutron QoS DSCP Marking Rule
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param qosDscpMarkingRule the Neutron QoS DSCP Marking Rule
     * @return the Neutron QoS DSCP Marking Rule
     */
    NetQosDscpMarkingRule create(String qosPolicyId, NetQosDscpMarkingRule qosDscpMarkingRule);

    /**
     * Updates a Neutron QoS DSCP Marking Rule
     *
     * @param qosPolicyId the Neutron QoS Policy identifier
     * @param ruleId the Neutron QoS DSCP Marking Rule identifier
     * @param qosDscpMarkingRule the Neutron QoS DSCP Marking Rule
     * @return the updated Neutron QoS DSCP Marking Rule
     */
    NetQosDscpMarkingRule update(String qosPolicyId, String ruleId, NetQosDscpMarkingRule qosDscpMarkingRule);
}
