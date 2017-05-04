package org.openstack4j.model.workflow.builder;

import org.openstack4j.model.sahara.builder.*;

/**
 * The Workflow service builders.
 */
public interface WorkflowBuilders {

    /**
     * The builder to create a workflow definition.
     *
     * @return the workflow definition builder.
     */
    public WorkflowDefinitionBuilder workflowDefinition();

}
