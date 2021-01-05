package org.openstack4j.model.network.qos;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.qos.builder.NetQosBandwidthLimitRuleBuilder;

/**
 * A Network (Neutron) QoS Bandwidth Limit Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosBandwidthLimitRule extends NetQosRule, IdEntity, Buildable<NetQosBandwidthLimitRuleBuilder> {
    /**
     * @return The maximum KBPS (kilobits per second) value.
     * If you specify this value, must be greater than 0 otherwise max_kbps will have no value.
     */
    Integer getMaxKbps();

    /**
     * @return The maximum burst size (in kilobits).
     */
    Integer getMaxBurstKbps();

    /**
     * @return The direction of the traffic to which the QoS rule is applied,
     * as seen from the point of view of the port. Valid values are egress and ingress.
     * Default value is egress.
     */
    String getDirection();
}
