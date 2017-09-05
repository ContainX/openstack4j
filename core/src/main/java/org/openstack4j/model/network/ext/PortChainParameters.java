package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortChainParametersBuilder;
import org.openstack4j.model.network.ext.builder.PortPairServiceFunctionParametersBuilder;

/**
 * Service function parameters. Currently,
 * only correlation=None|mpls and weight is supported.
 * Default correlation is None. Weight is an integer that
 * influences the selectionof a port pair within a port
 * pair group for a flow. The higher the weight, the more
 * flows will hash to the port pair. The default weight
 * is 1.
 *
 * @author Massimiliano Romano
 *
 */
public interface PortChainParameters extends ModelEntity,Buildable<PortChainParametersBuilder>{

	/**
	 * optional
	 * @return  correlation
	 */
	public String getCorrelation();

	/**
	 * optional
	 * @return symmetric
	 */
	public Boolean getSymmetric();

}
