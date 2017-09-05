package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortChainParameters;
import org.openstack4j.model.network.ext.PortChainUpdate;

import java.util.List;

/**
 * A Builder which creates a Port chain
 * 
 * @author Massimiliano Romano
 */
public interface PortChainUpdateBuilder extends Builder<PortChainUpdateBuilder, PortChainUpdate> {

	/**
	 * @see PortChainUpdate#getName()
	 */
	PortChainUpdateBuilder name(String name);
	

	
	/**
	 * @see PortChainUpdate#getDescription()
	 */
	PortChainUpdateBuilder description(String description);
	



	/**
	 * @see PortChainUpdate#getFlowClassifiers()
	 */
	PortChainUpdateBuilder flowClassifiers(List<String> flowClassifiers);

	/**
	 * @see PortChainUpdate#getPortPairGroups() ()
	 */
	PortChainUpdateBuilder portPairGroups(List<String> portPairGroups);





}
