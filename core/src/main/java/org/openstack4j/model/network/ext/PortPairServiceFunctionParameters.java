package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortPairServiceFunctionParametersBuilder;
import org.openstack4j.model.network.ext.builder.SessionPersistenceBuilder;

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
public interface PortPairServiceFunctionParameters extends ModelEntity,Buildable<PortPairServiceFunctionParametersBuilder>{

	/**
	 * optional
	 * @return  correlation
	 */
	public String getCorrelation();

	/**
	 * optional
	 * @return weight
	 */
	public Integer getWeight();

}
