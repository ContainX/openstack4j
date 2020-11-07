package org.openstack4j.model.heat.builder;

/**
 * The Orchestration builders
 */
public interface OrchestrationBuilders {

    /**
     * The builder to create a Template
     *
     * @return the TemplateBuilder
     */
    TemplateBuilder template();

    /**
     * The builder to create a StackCreate
     *
     * @return the StackCreate builder
     */
    StackCreateBuilder stack();

    /**
     * The builder to create a SoftwareConfig
     *
     * @return the software config builder
     */
    SoftwareConfigBuilder softwareConfig();

    /**
     * The builder to create a StackUpdate
     *
     * @return the StackUpdate builder
     */
    StackUpdateBuilder stackUpdate();

    /**
     * The builder to create a resource health update
     *
     * @return
     */
    ResourceHealthBuilder resourceHealth();

}
