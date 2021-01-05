package org.openstack4j.model.network.qos.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.network.qos.NetQosMinimumBandwidthRule;

/**
 * A Builder that creates a QoS Minimum Bandwidth Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosMinimumBandwidthRuleBuilder extends Buildable.Builder<NetQosMinimumBandwidthRuleBuilder, NetQosMinimumBandwidthRule> {

    /**
     * @see NetQosMinimumBandwidthRule#getMinKbps()
     */
    NetQosMinimumBandwidthRuleBuilder minKbps(Integer minKbps);

    /**
     * @see NetQosMinimumBandwidthRule#getDirection()
     */
    NetQosMinimumBandwidthRuleBuilder direction(String direction);
}
