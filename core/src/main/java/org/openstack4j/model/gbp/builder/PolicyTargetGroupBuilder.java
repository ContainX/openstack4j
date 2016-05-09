package org.openstack4j.model.gbp.builder;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyTargetGroup;
/**
 * A builder which produces a Policy Target Group object
 * 
 * @author vinod borole
 */
public interface PolicyTargetGroupBuilder extends Builder<PolicyTargetGroupBuilder, PolicyTargetGroup> {

    PolicyTargetGroupBuilder name(String name);
    PolicyTargetGroupBuilder description(String description);
    PolicyTargetGroupBuilder isShared(boolean shared);
    PolicyTargetGroupBuilder consumedPolicyRuleSets(List<String> policyRuleSet);
    PolicyTargetGroupBuilder providedPolicyRuleSets(List<String> policyRuleSet);
    PolicyTargetGroupBuilder policyTargets(List<String> policyTargets);
    PolicyTargetGroupBuilder networkServicePolicyId(String id);
    PolicyTargetGroupBuilder l2Policy(String id);
    PolicyTargetGroupBuilder serviceManagement(boolean serviceManagement);
}
 