package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortChainParameters;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;

/**
 * A builder to create and update a PortChainParameters
 * @author Massimiliano Romano
 *
 */
public interface PortChainParametersBuilder extends Builder<PortChainParametersBuilder, PortChainParameters>{

	/**
	 * required
	 * 
	 * @param correlation
	 * @return PortChainParametersBuilder
	 */
	public PortChainParametersBuilder correlation(String correlation);

	/**
	 * optional
	 * 
	 * @param symmetric
	 * @return PortChainParametersBuilder
	 */
	public PortChainParametersBuilder symmetric(boolean symmetric);
}


