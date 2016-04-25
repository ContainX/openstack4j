package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.ExternalPolicyBuilder;

public interface ExternalPolicy extends Resource , Buildable<ExternalPolicyBuilder>{

    List<PolicyRuleSet> getConsumedPolicyRuleSets();

    List<PolicyRuleSet> getProvidedPolicyRuleSets();

    List<String> getExternalSegments();

    String getDescription();

    boolean isShared();

} 
