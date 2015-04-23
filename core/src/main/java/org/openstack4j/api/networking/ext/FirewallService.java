package org.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.ext.Firewall;
import org.openstack4j.model.network.ext.FirewallUpdate;

/**
 * Networking (Neutron) FwaaS Firewall Extension API
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallService extends RestService {
    /**
     * List all Firewall(s) that the current tenant has access to.
     *
     * @return list of all Firewall(s)
     */
    List<? extends Firewall> list();

    /**
     * Returns list of Firewall(s) filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return filtered list of Firewall(s)
     */
    List<? extends Firewall> list(Map<String, String> filteringParams);

    /**
     * Get the specified Firewall by ID
     *
     * @param firewallId the Firewall identifier
     * @return the Firewall or null if not found
     */
    Firewall get(String firewallId);
    
    /**
     * Delete the specified Firewall by ID
     * @param firewallId the Firewall identifier
     * @return the action response
     */
    ActionResponse delete(String firewallId);
    
    /**
     * Create a Firewall
     * @param firewall 
     * @return Firewall
     */
    Firewall create(Firewall firewall);
    
    /**
     * Update a Firewall
     * @param firewallId the Firewall identifier
     * @param firewallUpdate FirewallUpdate
     * @return Firewall
     */
    Firewall update(String firewallId, FirewallUpdate firewallUpdate);
}
