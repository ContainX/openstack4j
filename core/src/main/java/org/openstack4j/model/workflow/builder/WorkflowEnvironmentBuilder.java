package org.openstack4j.model.workflow.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.workflow.WorkflowEnvironment;

/**
 * Builder for a {@link WorkflowEnvironment} model class
 * 
 * @author Renat Akhmerov
 */
public interface WorkflowEnvironmentBuilder extends Builder<WorkflowEnvironmentBuilder, WorkflowEnvironment> {

	/**
	 * @see WorkflowEnvironment#getId()
	 */
	WorkflowEnvironmentBuilder id(String id);

	// TODO(rakhmerov): add all methods
}
