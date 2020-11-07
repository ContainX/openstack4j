package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.LoadBalancerV2UpdateBuilder;

/**
 * an entity used to update an lbaas v2 loadbalancer
 *
 * @author emjburns
 */
public interface LoadBalancerV2Update extends ModelEntity, Buildable<LoadBalancerV2UpdateBuilder> {
    /**
     * Optional
     *
     * @see LoadBalancerV2#getDescription()
     */
    String getDescription();

    /**
     * Optional
     *
     * @see LoadBalancerV2#getName()
     */
    String getName();

    /**
     * Optional
     *
     * @see LoadBalancerV2#isAdminStateUp()
     */
    boolean isAdminStateUp();
}
