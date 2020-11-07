package org.openstack4j.model.compute.builder;

/**
 * The Compute Builders
 */
public interface ComputeBuilders {

    /**
     * The builder to create a Server
     *
     * @return the server create builder
     */
    ServerCreateBuilder server();

    /**
     * The builder to create a Block Device Mapping
     *
     * @return the block device mapping builder
     */
    BlockDeviceMappingBuilder blockDeviceMapping();

    /**
     * The builder to create a Flavor.
     *
     * @return the flavor builder
     */
    FlavorBuilder flavor();

    /**
     * The builder to create a Compute/Nova Floating IP
     *
     * @return the floating ip builder
     */
    FloatingIPBuilder floatingIP();

    /**
     * This builder which creates a QuotaSet for updates
     *
     * @return the QuotaSet update builder
     */
    QuotaSetUpdateBuilder quotaSet();

}
