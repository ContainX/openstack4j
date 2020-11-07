package org.openstack4j.model.sahara.builder;

/**
 * The Data Processing (Sahara) builders
 */
public interface DataProcessingBuilders {

    /**
     * The builder to create a sahara cluster
     *
     * @return the cluster builder
     */
    ClusterBuilder cluster();

    /**
     * The builder to create a sahara cluster template
     *
     * @return the cluster template builder
     */
    ClusterTemplateBuilder clusterTemplate();

    /**
     * The builder to create a sahara node group
     *
     * @return the node group builder
     */
    NodeGroupBuilder nodeGroup();

    /**
     * The builder to create a sahara node group template
     *
     * @return the node group template builder
     */
    NodeGroupTemplateBuilder nodeGroupTemplate();

    /**
     * The builder to create a sahara service configuration
     *
     * @return the service configuration builder
     */
    ServiceConfigBuilder serviceConfig();

    /**
     * The builder which creates a sahara Data Source
     *
     * @return the data source builder
     */
    DataSourceBuilder dataSource();

    /**
     * The builder which creates a sahara Job Binary
     *
     * @return the job binary builder
     */
    JobBinaryBuilder jobBinary();

    /**
     * The builder which creates a sahara Job
     *
     * @return the job builder
     */
    JobBuilder job();

    /**
     * The builder which creates a job configuration for sahara job execution
     *
     * @return the job config builder
     */
    JobConfigBuilder jobConfig();

    /**
     * The builder which creates a sahara job execution
     *
     * @return the job execution builder
     */
    JobExecutionBuilder jobExecution();


}
