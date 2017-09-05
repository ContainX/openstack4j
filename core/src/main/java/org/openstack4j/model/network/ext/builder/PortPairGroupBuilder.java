package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.model.network.ext.PortPairGroupParameters;

import java.util.List;

/**
 * A Builder which creates a Port pair group
 * 
 * @author Massimiliano Romano
 */
public interface PortPairGroupBuilder extends Builder<PortPairGroupBuilder, PortPairGroup> {

	/**
	 * @see PortPairGroup#getName()
	 */
	PortPairGroupBuilder name(String name);
	

	
	/**
	 * @see PortPairGroup#getDescription()
	 */
	PortPairGroupBuilder description(String description);
	

	
	/**
	 * @see PortPairGroup#getTenantId()
	 */
	PortPairGroupBuilder tenantId(String tenantId);

	/**
	 * @see PortPairGroup#getProjectId()
	 */
	PortPairGroupBuilder projectId(String projectId);


	/**
	 * @see PortPairGroup#getProjectId()
	 */
	PortPairGroupBuilder portPairs(List<String> portPairs);


	/**
	 * @see PortPairGroup#getPortPairGroupParameters()
	 */
	PortPairGroupBuilder portPairGroupParameters(PortPairGroupParameters portPairGroupParameters);





}
