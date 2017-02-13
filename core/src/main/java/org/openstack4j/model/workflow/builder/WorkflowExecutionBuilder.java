package org.openstack4j.model.workflow.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.workflow.WorkflowExecution;

/**
 * Builder for a {@link WorkflowExecution} model class
 * 
 * @author Renat Akhmerov
 */
public interface WorkflowExecutionBuilder extends Builder<WorkflowExecutionBuilder, WorkflowExecution> {

	/**
	 * @see WorkflowExecution#getId()
	 */
	WorkflowExecutionBuilder id(String id);

	// TODO(rakhmerov): add all methods
}
