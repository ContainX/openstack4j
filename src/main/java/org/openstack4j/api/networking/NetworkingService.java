package org.openstack4j.api.networking;

import org.openstack4j.common.RestService;

/**
 * OpenStack Networking Operations API
 * 
 * @author Jeremy Unruh
 */
public interface NetworkingService extends RestService {

	/**
	 * @return the Network Service API
	 */
	NetworkService network();
	
	/**
	 * @return the Subnet Service API
	 */
	SubnetService subnet();
	
	/**
	 * @return the Port Service API
	 */
	PortService port();
	
	/**
	 * @return the Router Service API
	 */
	RouterService router();
	
	/**
	 * @return the FloatingIP Service API
	 */
	FloatingIPService floatingip();
}
