package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.Network;

/**
 * OpenStack (Neutron) Network based Operations
 * 
 * @author Jeremy Unruh
 */
public interface NetworkService extends RestService {

	/**
	 * Lists the networks to which the current authorized tenant has access
	 * 
	 * @return List of Network
	 */
	List<? extends Network> list();
	
	/**
	 * Gets the network by ID
	 * 
	 * @param networkId the network identifier
	 * @return the Network or null if not found
	 */
	Network get(String networkId);
	
	/**
	 * Deletes a specified network and its associated resources
	 *  
	 * @param networkId the network identifier
	 * @return the action response
	 */
	ActionResponse delete(String networkId);

	/**
	 * Creates a new Network
	 * 
	 * @param network the network to create
	 * @return the newly created network
	 */
	Network create(Network network);
	
}
