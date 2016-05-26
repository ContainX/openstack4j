package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.MeteringLabel;

/**
 * A Builder which creates a Metering Label entity
 * 
 * @author Caio Bergamasco
 */
public interface MeteringLabelBuilder extends Builder<MeteringLabelBuilder, MeteringLabel>{

    /**
     * See {@link MeteringLabel#getName()} for details
     */
    MeteringLabelBuilder name(String name);
 
    /**
     * See {@link MeteringLabel#getDescription()} for details
     */
    MeteringLabelBuilder description(String description);
    
    /**
     * See {@link MeteringLabel#getTenantId()} for details
     */
    MeteringLabelBuilder tenantId(String tenantId);
    
}
