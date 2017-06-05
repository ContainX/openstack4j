/*
 * 
 */
package org.openstack4j.model.workflow;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.workflow.builder.TaskExecutionBuilder;

import java.util.Map;


/**
 * A task execution.
 *
 * @author Renat Akhmerov
 */
public interface TaskExecution extends Execution, Buildable<TaskExecutionBuilder> {
    /**
     * @return The task name.
     */
    String getName();

    /**
     * TODO: do we need a enum for task types?
     *
     * @return The task type.
     */
    String getType();

    /**
     * @return The ID of the workflow definition that this task belongs to.
     */
    String getWorkflowDefinitionId();

    /**
     * @return The ID of the workflow execution that this task belongs to.
     */
    String getWorkflowExecutionId();

    /**
     * @return The result of this task.
     */
    Map<?, ?> getResult();

    /**
     * @return The variables published into workflow context by this task.
     */
    Map<?, ?> getPublished();

    /**
     * @return {@code True} if this task is fully processed (all decisions made based on its result).
     */
    Boolean isProcessed();
}
