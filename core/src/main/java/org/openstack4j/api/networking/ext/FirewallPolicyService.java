package org.openstack4j.api.networking.ext;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.ext.Firewall;
import org.openstack4j.model.network.ext.FirewallUpdate;

/**
 * <p>Networking (Neutron) FwaaS Firewall Policy Extension API</p>
 * 
 * <p>Represents an ordered collection of firewall rules. A firewall policy can be shared across tenants. 
 * 		Thus it can also be made part of an audit workflow wherein the firewall_policy can be audited by the 
 * 		relevant entity that is authorized (and can be different from the tenants which create or use the firewall policy).
 * </p>
 * 
 * <p>
	 * The FWaaS extension provides OpenStack users with the ability to deploy firewalls to protect their networks. The FWaaS extension enables you to:
	 * <ul>
	 * 		<li>Apply firewall rules on traffic entering and leaving tenant networks.</li>
	 * 		<li>Support for applying tcp, udp, icmp, or protocol agnostic rules.</li>
	 * 		<li>Creation and sharing of firewall policies which hold an ordered collection of the firewall rules.</li>
	 * 		<li>Audit firewall rules and policies.</li>
	 * </ul>
 * </p>
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallPolicyService extends RestService {
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
