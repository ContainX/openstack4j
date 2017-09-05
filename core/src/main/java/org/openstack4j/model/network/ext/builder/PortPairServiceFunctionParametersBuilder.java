package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.SessionPersistence;
import org.openstack4j.model.network.ext.SessionPersistenceType;

/**
 * A builder to create and update a PortChainParameters
 * @author Massimiliano Romano
 *
 */
public interface PortPairServiceFunctionParametersBuilder extends Builder<PortPairServiceFunctionParametersBuilder, PortPairServiceFunctionParameters>{

	/**
	 * required
	 * 
	 * @param correlation
	 * @return PortChainParametersBuilder
	 */
	public PortPairServiceFunctionParametersBuilder correlation(String correlation);

	/**
	 * optional
	 * 
	 * @param weight
	 * @return PortChainParametersBuilder
	 */
	public PortPairServiceFunctionParametersBuilder weight(int weight);
}


