package org.openstack4j.api.compute;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.Flavor;

/**
 * Flavor service provides CRUD capabilities for Flavor(s).  A flavor is an available hardware configuration/template for a server
 * 
 * @author Jeremy Unruh
 */
public interface FlavorService extends RestService {
	
	/**
	 * List all Flavors
	 * 
	 * @return List of Flavor
	 */
	List<? extends Flavor> list();
	
	/**
	 * Get a Flavor by it's identifier
	 * 
	 * @param flavorId the flavor identifier
	 * @return Flavor
	 */
	Flavor get(String flavorId);
	
	/**
	 * Deletes a Flavor by it's identifier
	 * 
	 * @param flavorId the flavor identifier
	 * @return the action response
	 */
	ActionResponse delete(String flavorId);
	
	/**
	 * Creates a new Flavor
	 * 
	 * @param flavor the flavor to create
	 * @return the created flavor
	 */
	Flavor create(Flavor flavor);

	/**
	 * Creates a new Flavor
	 * 
	 * @param name the descriptive name of the flavor
	 * @param ram the Memory in MB for the flavor
	 * @param vcpus the Number of VCPUs for the flavor
	 * @param disk the size of the local disk in GB
	 * @param  the space in GB that will disappear when the VM is terminated (default is 0) [OPTIONAL]
	 * @param swap the Swap space in MB
	 * @param rxtxFactor the RX/TX factor (default is 1) [OPTIONAL]
	 * @param isPublic makes the flavor accessible to the public (the default is true).
	 * @return the created flavor
	 */
	Flavor create(String name, int ram, int vcpus, int disk, int ephemeral, int swap, float rxtxFactor, boolean isPublic);
	
	/**
	 * list extra specs
	 * 
	 * @param flavorId
	 * @return all extra specs for this flavor
	 */
	Map<String, String> listExtraSpecs(String flavorId);

	/**
	 * post a key-value map, if key exist, value will be updated, if not ,new
	 * extra spec created. openstack provides one api to support both create and
	 * update extra spec
	 * 
	 * @param flavorId
	 * @param spec
	 * @return spec
	 */
	Map<String, String> createAndUpdateExtraSpecs(String flavorId, Map<String, String> spec);

	/**
	 * delete the extra spec with the key and flavorId
	 * 
	 * @param flavorId
	 * @param key
	 */
	void deleteExtraSpecs(String flavorId, String key);

	/**
	 * get the extra spec's value by the key
	 * 
	 * @param flavorId
	 * @param key
	 * @return value
	 */
	String getSpec(String flavorId, String key);
	
}
