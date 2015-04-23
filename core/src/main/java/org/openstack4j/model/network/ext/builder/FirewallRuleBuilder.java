package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.FirewallRule;

/**
 * A Builder to Create Firewall Rule of FwaaS
 * 
 * @author Vishvesh Deshmukh
 */
public interface FirewallRuleBuilder extends Builder<FirewallRuleBuilder, FirewallRule> {

	/**
	 * @param tenantId : Owner of the Firewall. Only an administrative user can
	 *         specify a tenant ID other than its own.
	 * @return FirewallBuilder
	 */
	public FirewallRuleBuilder tenantId(String tenantId);
	
	/**
	 * @param name : Human readable name for the firewall (255 characters limit). Does not have to be unique.
	 * @return FirewallBuilder
	 */
	public FirewallRuleBuilder name(String name);

	/**
	 * @param description : Human readable description for the firewall (1024 characters limit).
	 * @return FirewallBuilder
	 */
	public FirewallRuleBuilder description(String description);
	
	/**
	 * @param adminstateup :  The administrative state of the firewall,
	 *         which is up (true) or down (false).
	 * @return FirewallBuilder
	 */
	public FirewallRuleBuilder adminStateUp(Boolean adminStateUp);

	/**
	 * shared :  When set to True makes this firewall rule visible to tenants other 
	 * 					 than its owner, and can be used in firewall policies not owned by its tenant.
	 * @return FirewallBuilder
	 */
	public FirewallRuleBuilder shared(Boolean shared);
	
	/**
	 * @param policyid : The firewall policy uuid that this firewall is associated with. 
	 * 				This firewall will implement the rules contained in the firewall policy represented by this uuid.
	 * @return FirewallBuilder
	 */
	public FirewallRuleBuilder policy(String policyId);
}
