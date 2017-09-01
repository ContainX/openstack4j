package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPairGroupUpdate;
import org.openstack4j.model.network.ext.PortPairGroup;
import java.util.List;

/**
 * A Builder which creates a Port Pair Group
 * 
 * @author Massimiliano Romano
 */
public interface PortPairGroupUpdateBuilder extends Builder<PortPairGroupUpdateBuilder, PortPairGroupUpdate> {

	/**
	 * @see PortPairGroup#getName()
	 */
	PortPairGroupUpdateBuilder name(String name);
	

	/**
	 * @see PortPairGroup#getDescription()
	 */
	PortPairGroupUpdateBuilder description(String description);

	/**
	 * @see PortPairGroup#getPortPairs()
	 */
	PortPairGroupUpdateBuilder portPairs(List<String> portPairs);



}
