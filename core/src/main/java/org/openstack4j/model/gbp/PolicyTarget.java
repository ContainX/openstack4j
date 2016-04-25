package org.openstack4j.model.gbp;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyTargetBuilder;

public interface PolicyTarget extends Resource, Buildable<PolicyTargetBuilder> {

    String getPortId();

    String getPolicyTargetGroupId();

    String getClusterId();

    String getDescription();

}
 