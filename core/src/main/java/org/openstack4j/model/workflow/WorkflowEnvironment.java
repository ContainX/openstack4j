/*
 * 
 */
package org.openstack4j.model.workflow;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.workflow.builder.WorkflowEnvironmentBuilder;


/**
 * A workflow environment.
 *
 * @author Renat Akhmerov
 */
public interface WorkflowEnvironment extends ModelEntity, Buildable<WorkflowEnvironmentBuilder> {
    /**
     * @return The id of this execution.
     */
    String getId();

    // TODO
}
