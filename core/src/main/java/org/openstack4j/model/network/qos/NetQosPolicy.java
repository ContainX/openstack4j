package org.openstack4j.model.network.qos;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.qos.builder.NetQosPolicyBuilder;

/**
 * A Network (Neutron) QoS Policy
 *
 * @author Guoshuai Li
 */
public interface NetQosPolicy extends Resource, Buildable<NetQosPolicyBuilder> {

    /**
     * @return Indicates whether this policy is shared across all projects.
     */
    boolean isShared();

    /**
     * @return If true, the QoS policy is the default policy.
     */
    boolean isDefault();

    /**
     * @return A set of zero or more policy rules.
     */
    List<? extends NetQosRule> getRules();
}
