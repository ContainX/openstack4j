package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.MeteringDirection;
import org.openstack4j.model.network.MeteringRule;

/**
 * A Builder which creates a Metering Rule entity
 * 
 * @author Caio Bergamasco
 */
public interface MeteringRuleBuilder extends Builder<MeteringRuleBuilder, MeteringRule>{

    /**
     * See {@link MeteringRule#getDirection()} for details
     */
    MeteringRuleBuilder direction(MeteringDirection direction);
 
    /**
     * See {@link MeteringRule#getMeteringLabelId()} for details
     */
    MeteringRuleBuilder meteringLabelId(String meteringLabelId);
    
    /**
     * See {@link MeteringRule#getRemoteIpPrefix()} for details
     */
    MeteringRuleBuilder remoteIpPrefix(String remoteIpPrefix);
    
    /**
     * See {@link MeteringRule#getExcluded()} for details
     */
    MeteringRuleBuilder excluded(Boolean excluded);
    
}
