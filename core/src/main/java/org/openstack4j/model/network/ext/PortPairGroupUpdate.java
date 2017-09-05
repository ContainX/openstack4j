package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortPairGroupUpdateBuilder;
import org.openstack4j.model.network.ext.builder.PortPairUpdateBuilder;

import java.util.List;

/**
 * a port pair group update
 * 
 * @author Massimiliano Romano
 */



public interface PortPairGroupUpdate extends ModelEntity, Buildable<PortPairGroupUpdateBuilder> {


	/**
	 * @return Human-readable description for the port pair group
	 */
	public String getDescription();


	/**
	 *
	 * 
	 * @return Human-readable name for the port pair group. Does not have to be unique.
	 */
	public String getName();

	/**
	 *
	 * @return List of port pairs.
	 */
	public List<String> getPortPairs();



	
}
