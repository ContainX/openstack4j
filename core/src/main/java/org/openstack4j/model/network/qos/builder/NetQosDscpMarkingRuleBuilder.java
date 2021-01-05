package org.openstack4j.model.network.qos.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.network.qos.NetQosDscpMarkingRule;

/**
 * A Builder that creates a QoS Dscp Marking Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosDscpMarkingRuleBuilder extends Buildable.Builder<NetQosDscpMarkingRuleBuilder, NetQosDscpMarkingRule> {

    /**
     * @see NetQosDscpMarkingRule#getDscpMark()
     */
    NetQosDscpMarkingRuleBuilder dscpMark(Integer dscpMark);
}
