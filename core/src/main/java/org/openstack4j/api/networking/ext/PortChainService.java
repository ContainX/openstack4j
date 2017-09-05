package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortChainUpdate;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.model.network.ext.PortPairGroupUpdate;

import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron) Port Chain Extension API
 * @author Massimiliano Romano
 *
 */
public interface PortChainService extends RestService {
    /**
     * List all port chains that the current tenant has access to
     *
     * @return list of all port chains
     */
    List<? extends PortChain> list();

    /**
     * Returns list of port chains filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return list of port pair groups fitered by filteringParams
     */
    List<? extends PortChain> list(Map<String, String> filteringParams);


    /**
     * Get the specified port chain by ID
     *
     * @param portChainId the port chain identifier
     * @return the port chain or null if not found
     */
    PortChain get(String portChainId);
    
    /**
     * Delete the specified port chain by ID
     * @param portChainId the port chain identifier
     * @return the action response
     */
    ActionResponse delete(String portChainId);
    /**
     * Create a port chain
     * @param portChain port chain
     * @return port chain
     */
    PortChain create(PortChain portChain);
    /**
     * Update a port chain
     * @param portChainId the port chain identifier
     * @param portChainUpdate PortChainUpdate
     * @return PortChain
     */
    PortChain update(String portChainId, PortChainUpdate portChainUpdate);
}
