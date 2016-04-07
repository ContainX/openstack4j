package org.openstack4j.model.network;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.builder.MeteringRuleBuilder;

/**
 * Metering Rule
 * 
 * @author Caio Bergamasco
 */
public interface MeteringRule extends ModelEntity, Buildable<MeteringRuleBuilder> {

    /**
     * Description for the metering label rule
     * 
     * @return description for the metering label rule
     */
    MeteringDirection getDirection();

    void setDirection(MeteringDirection direction);
    
    /**
     * Identifier for the metering label rule
     * 
     * @return identifier for the metering label rule
     */
    String getMeteringLabelId();

    void setMeteringLabelId(String meteringLabelId);
    
    /**
     * True if the prefix in the rule should be ignored
     * 
     * @return boolean if the prefix in the metering label rule is ignored
     */
    Boolean getExcluded();

    void setExcluded(Boolean excluded);
    
    /**
     * IP Prefix for the metering label rule
     * 
     * @return ip prefix for the metering label rule 
     */
    String getRemoteIpPrefix();

    void setRemoteIpPrefix(String remoteIpPrefix);
    
    
}
