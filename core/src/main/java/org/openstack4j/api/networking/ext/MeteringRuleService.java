package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.MeteringRule;

/**
 * Networking (Neutron) Metering Rule Extension API
 * 
 * @author Caio Bergamasco
 */
public interface MeteringRuleService extends RestService {
    
    /**
     * Lists metering rules 
     * 
     * @return the list of metering rules
     */
    List<? extends MeteringRule> get();
    
    /**
     * Shows the specified metering rule details
     * 
     * @param meteringId the metering rule identifier
     * @return the detailed metering rule
     */
    MeteringRule get(String meteringId);
    
    /**
     * Creates a new metering rule
     * 
     * @param meteringRule the metering rule to create
     * @return the newly created metering rule
     */
    MeteringRule create(MeteringRule meteringRule);

    /**
     * Delete a metering rule by ID
     * 
     * @param meteringRuleId the metering rule identifier to delete
     * @return the action response
     */
    ActionResponse delete(String meteringRuleId);
}
