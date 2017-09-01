package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.model.network.ext.PortPairGroupUpdate;


import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron) Port Pair Group Extension API
 * @author Massimiliano Romano
 *
 */
public interface PortPairGroupService extends RestService {
    /**
     * List all port pair groups  that the current tenant has access to
     *
     * @return list of all port pair groups
     */
    List<? extends PortPairGroup> list();

    /**
     * Returns list of port pair groups filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of port pair groups fitered by filteringParams
     */
    List<? extends PortPairGroup> list(Map<String, String> filteringParams);


    /**
     * Get the specified port pair group by ID
     *
     * @param portPairGroupId the port pair group identifier
     * @return the port pair group or null if not found
     */
    PortPairGroup get(String portPairGroupId);
    
    /**
     * Delete the specified port pair group by ID
     * @param portPairGroupId the port pair group identifier
     * @return the action response
     */
    ActionResponse delete(String portPairGroupId);
    /**
     * Create a port pair group
     * @param portPairGroup port pair group
     * @return port pair group
     */
    PortPairGroup create(PortPairGroup portPairGroup);
    /**
     * Update a port pair group
     * @param portPairGroupId the port pair group identifier
     * @param portPairGroupUpdate PortPairGroupUpdate
     * @return PortPairGroup
     */
    PortPairGroup update(String portPairGroupId, PortPairGroupUpdate portPairGroupUpdate);
}
