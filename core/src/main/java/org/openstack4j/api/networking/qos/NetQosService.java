package org.openstack4j.api.networking.qos;

/**
 * Provides Neutron QoS services.
 *
 * @author Guoshuai Li
 */
public interface NetQosService {

    /**
     * @return the QoS Rule Type API
     */
    NetQosRuleTypeService qosRuleType();

    /**
     * @return the QoS Policy Service API
     */
    NetQosPolicyService qosPolicy();

    /**
     * @return the QoS Bandwidth Limit Rule Service API
     */
    NetQosBandwidthLimitRuleService qosBandwidthLimitRule();

    /**
     * @return the QoS DSCP Marking Rule Service API
     */
    NetQosDscpMarkingRuleService qosDscpMarkingRule();

    /**
     * @return the QoS Minimum Bandwidth Rule Service API
     */
    NetQosMinimumBandwidthRuleService qosMinimumBandwidthRule();
}
