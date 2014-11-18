package org.openstack4j.model.compute;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.ModelEntity;

/**
 * An OpenStack ServerGroup which is a group that the server in it must be Located on different hosts
 * 
 * @author Octopus Zhang
 */
public interface ServerGroup extends ModelEntity {

	/**
	 * @return the id of this group
	 */
	String getId();
	
	/**
	 * @return the name of this group
	 */
	String getName();
	
	/**
	 * @return the servers in this group
	 */
	List<String> getMembers();
	
	/**
	 * @return the metadata of this group
	 */
	Map<String, String> getMetadata();
	
	/**
	 * @return the polices of this group
	 */
	List<String> getPolicies();
}
