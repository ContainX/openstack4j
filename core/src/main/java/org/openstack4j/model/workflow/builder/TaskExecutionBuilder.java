package org.openstack4j.model.workflow.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.workflow.TaskExecution;

/**
 * Builder for a {@link TaskExecution} model class
 * 
 * @author Renat Akhmerov
 */
public interface TaskExecutionBuilder extends Builder<TaskExecutionBuilder, TaskExecution> {

	/**
	 * @see TaskExecution#getId()
	 */
	TaskExecutionBuilder id(String id);

	// TODO(rakhmerov): add all methods
}
