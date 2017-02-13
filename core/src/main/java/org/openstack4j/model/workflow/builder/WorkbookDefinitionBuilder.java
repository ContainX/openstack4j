package org.openstack4j.model.workflow.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.workflow.WorkbookDefinition;

/**
 * Builder for a {@link WorkbookDefinition} model class
 * 
 * @author Renat Akhmerov
 */
public interface WorkbookDefinitionBuilder extends Builder<WorkbookDefinitionBuilder, WorkbookDefinition> {

	/**
	 * @see WorkbookDefinition#getName()
	 */
	WorkbookDefinitionBuilder name(String name);

	// TODO(rakhmerov): add all methods
}
