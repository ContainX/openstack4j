package org.openstack4j.model.workflow.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.workflow.ActionExecution;

/**
 * Builder for a {@link ActionExecution} model class
 * 
 * @author Renat Akhmerov
 */
public interface ActionExecutionBuilder extends Builder<ActionExecutionBuilder, ActionExecution> {

	/**
	 * @see ActionExecution#getId()
	 */
	ActionExecutionBuilder id(String id);

	// TODO(rakhmerov): add all methods
}
