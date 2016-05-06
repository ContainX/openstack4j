package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyAction;
import org.openstack4j.model.gbp.PolicyAction.Protocol;

public interface PolicyActionBuilder extends Builder<PolicyActionBuilder, PolicyAction> {
    PolicyActionBuilder name(String name);
    PolicyActionBuilder description(String description);
    PolicyActionBuilder actionType(Protocol actionType);
    PolicyActionBuilder shared(boolean shared);
}
  