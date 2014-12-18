package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.api.Builders;
import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkUpdate;

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
	 * Updates a network associated by the specified {@code networkId}
	 * 
	 * @param networkId the network identifier
	 * @param network the network options to update (see {@link Builders#networkUpdate()}
	 * @return the updated network
	 */
	Network update(String networkId, NetworkUpdate network);
	
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
