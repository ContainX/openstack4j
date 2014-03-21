package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.network.Subnet;

/**
 * OpenStack (Neutron) Subnet based Operations
 * 
 * @author Jeremy Unruh
 */
public interface SubnetService extends RestService {

	/**
	 * List all the Subnet(s) which are authorized by the current Tenant
	 *
	 * @return the list of subnets or empty
	 */
	List<? extends Subnet> list();
	
	/**
	 * Gets a Subnet by ID
	 * 
	 * @param subnetId the subnet identifier
	 * @return the Subnet or null if not found
	 */
	Subnet get(String subnetId);
	
	/**
	 * Delete a Subnet by ID
	 * 
	 * @param subnetId the subnet identifier to delete
	 */
	void delete(String subnetId);
	
	/**
	 * Creates a new Subnet
	 * 
	 * @param subnet the subnet to create
	 * @return the newly created subnet
	 */
	Subnet create(Subnet subnet);
	
}
