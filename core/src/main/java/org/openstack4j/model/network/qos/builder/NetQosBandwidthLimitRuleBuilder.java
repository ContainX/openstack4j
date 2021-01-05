package org.openstack4j.model.network.qos.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.qos.NetQosBandwidthLimitRule;

/**
 * A Builder that creates a QoS Bandwidth Limit Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosBandwidthLimitRuleBuilder extends Builder<NetQosBandwidthLimitRuleBuilder, NetQosBandwidthLimitRule> {

    /**
     * @see NetQosBandwidthLimitRule#getMaxKbps()
     */
    NetQosBandwidthLimitRuleBuilder maxKbps(Integer maxKbps);

    /**
     * @see NetQosBandwidthLimitRule#getMaxBurstKbps()
     */
    NetQosBandwidthLimitRuleBuilder maxBurstKbps(Integer maxBurstKbps);

    /**
     * @see NetQosBandwidthLimitRule#getDirection()
     */
    NetQosBandwidthLimitRuleBuilder direction(String direction);
}
