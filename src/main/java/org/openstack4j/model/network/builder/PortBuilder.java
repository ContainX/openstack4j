package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.network.ExtraDhcpOptCreate;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.State;

/**
 * A Builder which creates a Neutron Port
 * 
 * @author Jeremy Unruh
 */
public interface PortBuilder extends Builder<PortBuilder, Port> {

	/**
	 * @see Port#getName()
	 */
	PortBuilder name(String name);
	
	/**
	 * @see Port#getTenantId()
	 */
	PortBuilder tenantId(String tenantId);
	
	/**
	 * @see Port#getTenantId()
	 */
	PortBuilder tenant(Tenant tenant);
	
	/**
	 * @see Port#getNetworkId()
	 */
	PortBuilder networkId(String networkId);
	
	/**
	 * @see Port#getDeviceId()
	 */
	PortBuilder deviceId(String deviceId);
	
	/**
	 * @see Port#getDeviceOwner()
	 */
	PortBuilder deviceOwner(String deviceOwner);
	
	/**
	 * @see Port#getMacAddress()
	 */
	PortBuilder macAddress(String macAddress);
	
	/**
	 * Adds a fixed IP to the current list of fixed IP Addresses
	 * @param address the IP Address
	 * @param subnetId the subnet identifier
	 * @return PortBuilder
	 * @see Port#getFixedIps()
	 */
	PortBuilder fixedIp(String address, String subnetId);
	
	/**
   * Removes a fixed IP from the current list of fixed IP Addresses
   * @param address the IP Address
   * @param subnetId the subnet identifier
   * @return PortBuilder
   * 
   */
	PortBuilder removeFixedIp(String address, String subnetId);
	
	/**
	 * @see Port#isAdminStateUp()
	 */
	PortBuilder adminState(boolean adminStateUp);
	
	/**
	 * @see Port#getState()
	 */
	PortBuilder state(State state);
	
        PortBuilder extraDhcpOpt(ExtraDhcpOptCreate extraDhcpOptCreate);
	
	PortBuilder securityGroup(String groupName);
	
}
