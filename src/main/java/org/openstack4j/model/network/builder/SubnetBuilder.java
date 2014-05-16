package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;

/**
 * A Builder which creates a Subnet
 * 
 * @author Jeremy Unruh
 */
public interface SubnetBuilder extends Builder<SubnetBuilder, Subnet> {
	
	/**
	 * @see Subnet#getName()
	 */
	SubnetBuilder name(String name);
	
	/**
	 * @see Subnet#getNetworkId()
	 */
	SubnetBuilder networkId(String networkId);
	
	/**
	 * @see Subnet#getNetworkId()
	 */
	SubnetBuilder network(Network network);
	
	/**
	 * @see Subnet#getIpVersion()
	 */
	SubnetBuilder ipVersion(IPVersionType ipVersion);
	
	/**
	 * @see Subnet#getCidr()
	 */
	SubnetBuilder cidr(String cidr);
	
	/**
	 * Adds a allocation pool
	 *
	 * @param start the starting IP
	 * @param end the ending IP
	 * @return the builder
	 */
	SubnetBuilder addPool(String start, String end);
	
	/**
	 * @see Subnet#getTenantId()
	 */
	SubnetBuilder tenantId(String tenantId);
	
	/**
	 * @see Subnet#getTenantId()
	 */
	SubnetBuilder tenant(Tenant tenant);
	
	/**
   * @see Subnet#isDHCPEnabled()
   */
	SubnetBuilder enableDHCP(boolean enable);
	
	
}
