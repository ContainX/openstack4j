package org.openstack4j.model.network.qos.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.qos.NetQosPolicy;

/**
 * A Builder that creates a QoS Policy
 *
 * @author Guoshuai Li
 */
public interface NetQosPolicyBuilder extends Builder<NetQosPolicyBuilder, NetQosPolicy> {

    /**
     * @see NetQosPolicy#getName()
     */
    NetQosPolicyBuilder name(String name);

    /**
     * @see NetQosPolicy#isShared()
     */
    NetQosPolicyBuilder isShared(boolean shared);

    /**
     * @see NetQosPolicy#isDefault()
     */
    NetQosPolicyBuilder isDefault(boolean isDefault);
}
