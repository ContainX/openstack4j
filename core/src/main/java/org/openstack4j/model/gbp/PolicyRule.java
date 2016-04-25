package org.openstack4j.model.gbp;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyRuleBuilder;

public interface PolicyRule extends Resource,Buildable<PolicyRuleBuilder> {

    boolean isEnabled();

    boolean isShared();

    List<PolicyAction> getPolicyActions();

    String getPolicyClassifierId();

    String getDescription();

}
 