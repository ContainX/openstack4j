/*
 * 
 */
package org.openstack4j.model.workflow;

import org.openstack4j.model.ModelEntity;

import java.util.Date;
import java.util.List;


/**
 * Base interface for all execution objects.
 * 
 * @author Renat Akhmerov
 */
public interface Execution extends ModelEntity {
	/**
	 * @return The id of this execution.
	 */
	String getId();

    /**
     * @return The description of this execution.
     */
    String getDescription();

    /**
     * @return The name of the workflow that this task belongs to.
     */
    String getWorkflowName();

    /**
	 * @return Execution state.
	 */
	State getState();

	/**
	 * @return Execution state info.
	 */
	String getStateInfo();

    /**
     * @return The list of tags.
     */
    List<String> getTags();

    /**
	 * @return The time that this entity was createdAt at.
	 */
	Date getCreated();

	/**
	 * @return The time that this entity was last updatedAt at.
	 */
	Date getUpdated();
}
