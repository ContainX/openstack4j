package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.QoSPolicy;

public interface BandwidthLimitRuleBuilder extends Builder<BandwidthLimitRuleBuilder, QoSPolicy.BandwidthLimitRule> {
    BandwidthLimitRuleBuilder maxKbps(Integer maxKbps);
    BandwidthLimitRuleBuilder maxBurstKbps(Integer maxBurstKbps);
    BandwidthLimitRuleBuilder direction(String direction);
}
