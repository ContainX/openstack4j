/*
 * 
 */
package org.openstack4j.model.workflow;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.workflow.builder.ActionExecutionBuilder;

import java.util.List;
import java.util.Map;


/**
 * An action execution.
 *
 * @author Renat Akhmerov
 */
public interface ActionExecution extends Execution, Buildable<ActionExecutionBuilder> {
    /**
     * @return The name of the corresponding task.
     */
    String getTaskName();

    /**
     * @return The id of the corresponding task execution.
     */
    String getTaskExecutionId();

    /**
     * @return The name of the action.
     */
    String getName();

    /**
     * @return {@code True} if the result of this action execution is accepted.
     */
    Boolean isAccepted();

    /**
     * @return The input parameters of this action execution.
     */
    Map<String, ?> getInput();

    /**
     * @return The output of this action execution.
     */
    Map<String, ?> getOutput();
}
