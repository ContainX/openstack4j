package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.PortPairGroupParameters;
import org.openstack4j.model.network.ext.PortPairServiceFunctionParameters;

import java.util.List;

/**
 * A builder to create and update a PortPairServiceFunctionParameters
 * @author Massimiliano Romano
 *
 */
public interface PortPairGroupParametersBuilder extends Builder<PortPairGroupParametersBuilder, PortPairGroupParameters>{

	/**
	 * required
	 * 
	 * @param lbFields
	 * @return PortPairGroupParametersBuilder
	 */
	public PortPairGroupParametersBuilder lbFields(List<String> lbFields);


}


