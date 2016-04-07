package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;

/**
 * Networking (Neutron) Metering Service
 * 
 * @author Caio Bergamasco
 */
public interface MeteringService extends RestService {
    
    /**
     * @return the Metering Label Service API
     */
    MeteringLabelService meteringLabel();
    
    /**
     * @return the Metering Rule Service API
     */
    MeteringRuleService meteringRule();
}
