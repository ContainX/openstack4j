package org.openstack4j.model.network.qos;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.qos.builder.NetQosDscpMarkingRuleBuilder;

/**
 * A Network (Neutron) QoS DSCP Marking Rule
 *
 * @author Guoshuai Li
 */
public interface NetQosDscpMarkingRule extends NetQosRule, IdEntity, Buildable<NetQosDscpMarkingRuleBuilder> {
    /**
     * @return The DSCP mark value.
     */
    Integer getDscpMark();
}
