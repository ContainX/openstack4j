package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.networking.SubnetService;

/**
 * OpenStack Networking Operations API
 * 
 * @author Jeremy Unruh
 */
public class NetworkingServiceImpl implements NetworkingService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NetworkService network() {
		return Apis.get(NetworkService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubnetService subnet() {
		return Apis.get(SubnetService.class);
	}

}
