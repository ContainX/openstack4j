package org.openstack4j.model.gbp.builder;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyRuleSet;
/**
 * A builder which produces a Policy Rule Set object
 * 
 * @author vinod borole
 */
public interface PolicyRuleSetBuilder extends Builder<PolicyRuleSetBuilder, PolicyRuleSet> {
    PolicyRuleSetBuilder name(String name);
    PolicyRuleSetBuilder description(String description);
    PolicyRuleSetBuilder shared(boolean shared);
    PolicyRuleSetBuilder rules(List<String> ruleIds);
}
 