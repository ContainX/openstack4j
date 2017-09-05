package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPair;

/**
 * A Builder which creates a Port pair
 * 
 * @author Massimiliano Romano
 */
public interface PortPairBuilder extends Builder<PortPairBuilder, PortPair> {

	/**
	 * @see PortPair#getName()
	 */
	PortPairBuilder name(String name);
	
	/**
	 * @see PortPair#getIngressPortId()
	 */
	PortPairBuilder ingressPortId(String ingressPortId);
	
	/**
	 * @see PortPair#getEgressPortId()
	 */	
	PortPairBuilder egressPortId(String egressPortId);
	
	/**
	 * @see PortPair#getDescription()
	 */
	PortPairBuilder description(String description);
	

	
	/**
	 * @see PortPair#getTenantId()
	 */
	PortPairBuilder tenantId(String tenantId);

	/**
	 * @see PortPair#getProjectId()
	 */
	PortPairBuilder projectId(String projectId);



}
