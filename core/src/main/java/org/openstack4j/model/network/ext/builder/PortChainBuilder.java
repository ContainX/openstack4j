package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortChainParameters;

import java.util.List;

/**
 * A Builder which creates a Port chain
 * 
 * @author Massimiliano Romano
 */
public interface PortChainBuilder extends Builder<PortChainBuilder, PortChain> {

	/**
	 * @see PortChain#getName()
	 */
	PortChainBuilder name(String name);
	

	
	/**
	 * @see PortChain#getDescription()
	 */
	PortChainBuilder description(String description);
	

	
	/**
	 * @see PortChain#getTenantId()
	 */
	PortChainBuilder tenantId(String tenantId);

	/**
	 * @see PortChain#getProjectId()
	 */
	PortChainBuilder projectId(String projectId);

	/**
	 * @see PortChain#getFlowClassifiers()
	 */
	PortChainBuilder flowClassifiers(List<String> flowClassifiers);

	/**
	 * @see PortChain#getPortPairGroups() ()
	 */
	PortChainBuilder portPairGroups(List<String> portPairGroups);

	/**
	 * @see PortChain#getPortChainParameters() ()
	 */
	PortChainBuilder portChainParameters(PortChainParameters portChainParameters);



}
