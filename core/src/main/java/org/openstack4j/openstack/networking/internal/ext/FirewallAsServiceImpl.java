package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.ext.FirewallAsService;
import org.openstack4j.api.networking.ext.FirewallService;

/**
 * OpenStack Firewall As a Service (FwaaS) Operations API
 * 
 * @author Vishvesh Deshmukh
 */
public class FirewallAsServiceImpl implements FirewallAsService {

	/**
     * {@inheritDoc}
     */
	@Override
	public FirewallService firewall() {
		return Apis.get(FirewallService.class);
	}
	
	/**
     * {@inheritDoc}
     */
	/*@Override
	public FirewallRuleService firewallrule() {
		return Apis.get(FirewallService.class);
	}*/
    
	/**
     * {@inheritDoc}
     */
	/*@Override
	public FirewallPolicyService firewallpolicy() {
		return Apis.get(FirewallService.class);
	}*/
}
