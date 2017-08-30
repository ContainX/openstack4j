package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;
import org.openstack4j.model.network.ext.SessionPersistence;
import org.openstack4j.model.network.ext.SessionPersistenceType;

/**
 * A builder to create and update a PortPairServiceFunctionParameters
 * @author Massimiliano Romano
 *
 */
public interface PortPairServiceFunctionParametersBuilder extends Builder<PortPairServiceFunctionParametersBuilder, PortPairServiceFunctionParameters>{

	/**
	 * required
	 * 
	 * @param correlation
	 * @return PortPairServiceFunctionParametersBuilder
	 */
	public PortPairServiceFunctionParametersBuilder correlation(String correlation);

	/**
	 * optional
	 * 
	 * @param weight
	 * @return PortPairServiceFunctionParametersBuilder
	 */
	public PortPairServiceFunctionParametersBuilder weight(int weight);
}


