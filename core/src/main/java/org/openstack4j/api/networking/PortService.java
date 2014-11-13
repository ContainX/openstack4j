package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.Port;

/**
 * OpenStack (Neutron) Port based Operations
 * 
 * @author Jeremy Unruh
 */
public interface PortService extends RestService {

	/**
	 * Lists all Ports authorized by the current Tenant
	 *
	 * @return the list of ports
	 */
	List<? extends Port> list();
	
	/**
	 * Gets the Port by ID
	 *
	 * @param portId the port identifier
	 * @return the port or null if not found
	 */
	Port get(String portId);
	
	/**
	 * Delete a Port by ID
	 * 
	 * @param portId the port identifier to delete
	 * @return the action response
	 */
	ActionResponse delete(String portId);
	
	/**
	 * Creates a new Port
	 * @param port the port to create
	 * @return the newly create Port
	 */
	Port create(Port port);
	
	/**
	 * Updates an existing Port.  The Port identifier must be set on the port object to be successful
	 * @param port the port to update
	 * @return the updated port
	 */
	Port update(Port port);
}
