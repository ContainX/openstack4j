package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.QoSPolicy.DscpMarkingRule;

import java.util.Set;

public interface DscpMarkingRuleBuilder extends Builder<DscpMarkingRuleBuilder, DscpMarkingRule> {
    DscpMarkingRuleBuilder dscpMark(Integer dscpMark);
    DscpMarkingRuleBuilder id(String id);
    DscpMarkingRuleBuilder tags(Set<String> tags);
}
