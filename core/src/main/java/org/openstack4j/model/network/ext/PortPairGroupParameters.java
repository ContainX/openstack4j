package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.PortPairGroupParametersBuilder;
import org.openstack4j.model.network.ext.builder.PortPairServiceFunctionParametersBuilder;

import java.util.List;

/**
 * Port Pair Group Parameters.
 * Currently, only a list of lb_fields is supported.
 *
 * @author Massimiliano Romano
 *
 */
public interface PortPairGroupParameters extends ModelEntity,Buildable<PortPairGroupParametersBuilder>{

	/**
	 * optional
	 * @return  lb_fields
	 */
	public List<String> getLbFields();



}
