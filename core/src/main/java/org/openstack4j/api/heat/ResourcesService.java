package org.openstack4j.api.heat;

import org.openstack4j.model.heat.Resource;

import java.util.List;


/**
 * This interface defines all methods for the manipulation of resources
 * 
 * @author Octopus Zhang
 * 
 */
public interface ResourcesService {
	
	/**
	 * Gets a list of currently existing {@link Resource}s for a specified stack.
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @return the list of {@link Resource}s
	 */
	List<? extends Resource> list(String stackName, String stackId);
	
	
	/**
	 * Gets a list of currently existing {@link Resource}s for a specified stack.
	 * 
	 * @param stackNameOrId    Stack name or stack id
	 * @return the list of {@link Resource}s
	 */
	List<? extends Resource> list(String stackNameOrId);

	/**
	 * Gets a list of currently existing {@link Resource}s for a specified stack with filtern parameters.
	 *
	 * @param stackNameOrId  	Stack name or stack id
	 * @param depth   			The recursion level for which resources will be listed.
	 * @return the list of {@link Resource}s
	 */
	List<? extends Resource> list(String stackNameOrId, int depth);
	
	/**
	 * Gets the detail of the specified resource
	 * 
	 * @param stackId  	   The unique identifier for a stack
	 * @param stackName    The name of a stack
	 * @param resourceName The name of a resource
	 * @return the detail of the specified resource
	 */
	Resource show(String stackName, String stackId, String resourceName);
}
