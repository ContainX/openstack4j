package org.openstack4j.model.compute.builder;

import org.openstack4j.openstack.compute.domain.*;

/**
 * The Compute Builders
 */
public interface ComputeBuilders {

    /**
     * The builder to create a Server
     *
     * @return the server create builder
     */
    public ServerCreateBuilder server();

    /**
     * The builder to create a Block Device Mapping
     *
     * @return the block device mapping builder
     */
    public BlockDeviceMappingBuilder blockDeviceMapping();

    /**
     * The builder to create a Flavor.
     *
     * @return the flavor builder
     */
    public FlavorBuilder flavor();

    /**
     * The builder to create a Compute/Nova Floating IP
     *
     * @return the floating ip builder
     */
    public FloatingIPBuilder floatingIP();

    /**
     * This builder which creates a QuotaSet for updates
     *
     * @return the QuotaSet update builder
     */
    public QuotaSetUpdateBuilder quotaSet();

}
