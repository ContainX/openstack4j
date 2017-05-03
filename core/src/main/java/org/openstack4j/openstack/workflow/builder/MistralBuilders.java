package org.openstack4j.openstack.workflow.builder;

import org.openstack4j.model.workflow.builder.WorkflowBuilders;
import org.openstack4j.model.workflow.builder.WorkflowDefinitionBuilder;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowDefinition;

/**
 * The Mistral v2 Builders
 */
public class MistralBuilders implements WorkflowBuilders {

    @Override
    public WorkflowDefinitionBuilder workflowDefinition() {
        return MistralWorkflowDefinition.builder();
    }
}
