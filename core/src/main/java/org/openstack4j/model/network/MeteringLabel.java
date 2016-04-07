package org.openstack4j.model.network;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.MeteringLabelBuilder;

/**
 * Metering Labels
 * 
 * @author Caio Bergamasco
 */
public interface MeteringLabel extends Resource, Buildable<MeteringLabelBuilder> {

    /**
     * Description for the metering label
     * 
     * @return description for the metering label
     */
    String getDescription();

    void setDescription(String description);
    
    
}
