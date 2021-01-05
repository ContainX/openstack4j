package org.openstack4j.model.network.qos;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.qos.builder.NetQosMinimumBandwidthRuleBuilder;

/**
 * A Network (Neutron) QoS Minimum Bandwid Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosMinimumBandwidthRule extends NetQosRule, IdEntity, Buildable<NetQosMinimumBandwidthRuleBuilder> {
    /**
     * @return The minimum KBPS (kilobits per second) value which should be available for port.
     */
    Integer getMinKbps();

    /**
     * @return The direction of the traffic to which the QoS rule is applied,
     * as seen from the point of view of the port. Valid values are egress and ingress.
     * Default value is egress.
     */
    String getDirection();
}
