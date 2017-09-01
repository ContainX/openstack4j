package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.FlowClassifierUpdate;

/**
 * A Builder which creates a Flow Classifier Update
 * 
 * @author Massimiliano Romano
 */
public interface FlowClassifierUpdateBuilder extends Builder<FlowClassifierUpdateBuilder, FlowClassifierUpdate> {

	/**
	 * @see FlowClassifier#getName()
	 */
	FlowClassifierUpdateBuilder name(String name);




	/**
	 * @see FlowClassifier#getDescription()
	 */
	FlowClassifierUpdateBuilder description(String description);






}
