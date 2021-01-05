package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.QoSPolicy.MinimumBandwidthRule;

import java.util.Set;

public interface MinimumBandwidthRuleBuilder extends Builder<MinimumBandwidthRuleBuilder, MinimumBandwidthRule> {
    MinimumBandwidthRuleBuilder minKbps(Integer minKbps);
    MinimumBandwidthRuleBuilder id(String id);
    MinimumBandwidthRuleBuilder direction(String direction);
    MinimumBandwidthRuleBuilder tags(Set<String> tags);
}
