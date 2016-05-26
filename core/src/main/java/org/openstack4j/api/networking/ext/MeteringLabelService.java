package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.MeteringLabel;

/**
 * Networking (Neutron) Metering Label Extension API
 * 
 * @author Caio Bergamasco
 */
public interface MeteringLabelService extends RestService {
    
    /**
     * Lists metering labels 
     * 
     * @return the list of metering labels
     */
    List<? extends MeteringLabel> get();
    
    /**
     * Shows the specified metering label
     * 
     * @param meteringId the metering label identifier
     * @return the metering label
     */
    MeteringLabel get(String meteringId);
    
    /**
     * Creates a new metering label
     * 
     * @param metering the metering label to create
     * @return the newly created metering label
     */
    MeteringLabel create(MeteringLabel metering);

    /**
     * Delete a metering label by ID
     * 
     * @param meteringId the metering label identifier to delete
     * @return the action response
     */
    ActionResponse delete(String meteringId);
}
