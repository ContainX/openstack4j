package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.ServerGroup;

public interface ServerGroupService extends RestService {
	
	/**
	 * Lists serverGroups that are associated with the account making the request
	 *
	 * @return the list of serverGroups
	 */
	List<? extends ServerGroup> list();
	
	/**
	 * Gets the serverGroup by id
	 *
	 * @param name the serverGroup id
	 * @return the serverGroup
	 */
	ServerGroup get(String id);
	
	/**
	 * Deletes the serverGroup by id
	 *
	 * @param name the serverGroup id
	 * @return the action response
	 */
	ActionResponse delete(String id);
	
	/**
	 * Generates or imports a serverGroup
	 *
	 * @param name the name of the serverGroup
	 * @param publicKey the public key (optional), Null indicates one will be generated
	 * @return the newly created serverGroup
	 */
	ServerGroup create(String name, String policy);

}
