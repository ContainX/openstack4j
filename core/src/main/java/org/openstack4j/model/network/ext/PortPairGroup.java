package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortPairBuilder;
import org.openstack4j.model.network.ext.builder.PortPairGroupBuilder;

import java.util.List;

/**
 * a port pair
 * 
 * @author Massimiliano Romano
 */

/*
		# neutron port-pair-group-show PPG1
		+----------------------------+--------------------------------------+
		| Field                      | Value                                |
		+----------------------------+--------------------------------------+
		| description                |                                      |
		| group_id                   | 1                                    |
		| id                         | fd90217c-9363-4e0b-a6d9-0aca637af9b0 |
		| name                       | PPG1                                 |
		| port_pair_group_parameters | {"lb_fields": []}                    |
		| port_pairs                 | 61dda57c-afe0-4967-a14c-6489f78030e5 |
		| project_id                 | fa30ea019b0d43d7a917b31f28a5efdb     |
		| tenant_id                  | fa30ea019b0d43d7a917b31f28a5efdb     |
		+----------------------------+--------------------------------------+

*/

public interface PortPairGroup extends ModelEntity, Buildable<PortPairGroupBuilder> {





	/**
	 * @return Human-readable description for the port pair group
	 */
	public String getDescription();

	/**
	 *
	 *
	 * @return The unique ID for the port pair group.
	 */
	public String getId();

	/**
	 *
	 *
	 * @return The group ID
	 */
	public String getGroupId();

	/**
	 *
	 * 
	 * @return Human-readable name for the VIP. Does not have to be unique.
	 */
	public String getName();



	/**
	 * 
	 * @return PortPairGroupParameters
	 */
	public PortPairGroupParameters getPortPairGroupParameters();



	/**
	 * 
	 * @return The Tenant Id.Owner of the Port Pair.
	 */
	public String getTenantId();


	/**
	 *
	 * @return The Project Id.Owner of the Port Pair.
	 */
	public String getProjectId();

	/**
	 *
	 * @return list of port pair ids
	 */
	public List<String> getPortPairs();
	
	
}
