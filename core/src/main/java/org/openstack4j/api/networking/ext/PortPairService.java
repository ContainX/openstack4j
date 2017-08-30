package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairUpdate;
import org.openstack4j.model.network.ext.VipUpdate;

import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron) Port pair Extension API
 * @author Massimiliano Romano
 *
 */
public interface PortPairService extends RestService {
    /**
     * List all port pairs  that the current tenant has access to
     *
     * @return list of all port pairs
     */
    List<? extends PortPair> list();

    /**
     * Returns list of port pairs filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of port pairs fitered by filteringParams
     */
    List<? extends PortPair> list(Map<String, String> filteringParams);


    /**
     * Get the specified port pair by ID
     *
     * @param portPairId the port pair identifier
     * @return the port pair or null if not found
     */
    PortPair get(String portPairId);
    
    /**
     * Delete the specified port pair by ID
     * @param portPairId the port pair identifier
     * @return the action response
     */
    ActionResponse delete(String portPairId);
    /**
     * Create a port pair
     * @param portPair port pair
     * @return port pair
     */
    PortPair create(PortPair portPair);
    /**
     * Update a port pair
     * @param portPairId the port pair identifier
     * @param portPairUpdate PortPairUpdate
     * @return PortPair
     */
    PortPair update(String portPairId, PortPairUpdate portPairUpdate);
}
