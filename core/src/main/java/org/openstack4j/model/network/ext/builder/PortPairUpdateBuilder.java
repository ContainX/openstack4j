package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairUpdate;

/**
 * A Builder which creates a Port Pair
 * 
 * @author Massimiliano Romano
 */
public interface PortPairUpdateBuilder extends Builder<PortPairUpdateBuilder, PortPairUpdate> {

	/**
	 * @see PortPair#getName()
	 */
	PortPairUpdateBuilder name(String name);
	

	/**
	 * @see PortPair#getDescription()
	 */
	PortPairUpdateBuilder description(String description);
	



}
