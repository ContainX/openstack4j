package org.openstack4j.model.gbp.builder;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyRule;

public interface PolicyRuleBuilder extends Builder<PolicyRuleBuilder, PolicyRule> {
    PolicyRuleBuilder name(String name);
    PolicyRuleBuilder description(String description);
    PolicyRuleBuilder shared(boolean shared);
    PolicyRuleBuilder classifier(String classifierId);
    PolicyRuleBuilder actions(List<String> actionIds);
}
 