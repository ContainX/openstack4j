package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyRuleSetBuilder;
import org.openstack4j.openstack.gbp.domain.GbpPolicyRule;

public interface PolicyRuleSet extends Resource, Buildable<PolicyRuleSetBuilder> {

    List<GbpPolicyRule> getPolicyRules();

    List<String> getChildPolicyRuleSets();

    String getParentId();

    boolean isShared();

    String getDescription();

}
  