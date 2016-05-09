package org.openstack4j.model.gbp.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.gbp.PolicyAction;
import org.openstack4j.model.gbp.PolicyAction.Protocol;
/**
 * A builder which produces a Policy Action Create object
 * 
 * @author vinod borole
 */
public interface PolicyActionCreateBuilder extends Builder<PolicyActionCreateBuilder, PolicyAction> {
    PolicyActionCreateBuilder name(String name);
    PolicyActionCreateBuilder description(String description);
    PolicyActionCreateBuilder actionType(Protocol actionType);
    PolicyActionCreateBuilder shared(boolean shared);
}
  