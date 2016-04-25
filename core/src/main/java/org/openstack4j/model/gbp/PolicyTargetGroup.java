package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyTargetGroupBuilder;

public interface PolicyTargetGroup extends Buildable<PolicyTargetGroupBuilder>, Resource {

    List<String> getSubnets();

    boolean isShared();

    boolean isServiceManagement();

    List<PolicyTarget> getPolicyTargets();

    String getNetworkServicePolicyId();

    String getL2PolicyId();

    List<PolicyRuleSet> getProvidedPolicyRuleSets();

    List<PolicyRuleSet> getConsumedPolicyRuleSets();

    String getDescription();

}
 