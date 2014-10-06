package org.openstack4j.model.network;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.SubnetBuilder;

/**
 * A Subnet is a network with Pools and network based settings
 * 
 * @author Jeremy Unruh
 */
public interface Subnet extends Resource, Buildable<SubnetBuilder> {

	/**
	 * @return true if DHCP is enabled for this subnet, false if not.
	 */
	boolean isDHCPEnabled();

	/**
	 * @return the id of the network this subnet is associated with
	 */
	String getNetworkId();

	/**
	 * @return the DNS server names/IP
	 */
	List<String> getDnsNames();

	/**
	 * @return the sub-ranges of cidr available for dynamic allocation to ports
	 */
	List<? extends Pool> getAllocationPools();


	/**
	 * @return the set of routes that should be used by devices with IPs from this subnet
	 */
	List<String> getHostRoutes();

	/**
	 * @return the ip version used by this subnet
	 */
	IPVersionType getIpVersion();


	/**
	 * @return the default gateway used by devices in this subnet
	 */
	String getGateway();

	/**
	 * @return the cidr representing the IP range for this subnet, based on IP version
	 */
	String getCidr();
}
