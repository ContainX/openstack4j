package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyActionUpdate;

public interface PolicyActionUpdateBuilder extends Builder<PolicyActionUpdateBuilder, PolicyActionUpdate>{
    PolicyActionUpdateBuilder name(String name);
    PolicyActionUpdateBuilder description(String description);
    PolicyActionUpdateBuilder shared(boolean shared);
}
