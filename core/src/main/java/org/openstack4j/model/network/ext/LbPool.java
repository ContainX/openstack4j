package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.LbPoolBuilder;

import java.util.List;

/**
 * A loadbanlance pool
 *
 * @author liujunpeng
 */
public interface LbPool extends ModelEntity, Buildable<LbPoolBuilder> {

    /**
     * @return id. The unique ID for the pool.
     */
    String getId();

    /**
     * @return tenantId.
     * Owner of the pool. Only an administrative user can specify a tenant ID other than its own.
     */
    String getTenantId();

    /**
     * @return vip Id.
     */
    String getVipId();

    /**
     * @return Pool name. Does not have to be unique.
     */
    String getName();

    /**
     * @return Description for the pool.
     */
    String getDescription();

    /**
     * @return subnetId The ID of the subnet on which to allocate the VIP address.
     */
    String getSubnetId();

    /**
     * @return The protocol of the pool, which is TCP, HTTP, or HTTPS.
     */
    Protocol getProtocol();

    /**
     * @return loadbalance provider,such as haproxy
     */
    String getProvider();

    /**
     * @return The load-balancer algorithm, which is round-robin, least-connections, and so on. This value, which must be supported, is dependent on the load-balancer provider. Round-robin must be supported.
     */
    LbMethod getLbMethod();

    /**
     * @return List of members that belong to the pool.
     */
    List<String> getMembers();

    /**
     * @return List of health monitors that associated to the pool.
     */
    List<String> getHealthMonitors();

    /**
     * @return The administrative state of the lb pool, which is up (true) or
     * down (false).
     */
    boolean isAdminStateUp();

    /**
     * @return status The status of the lb pool. Indicates whether the lb pool is
     * operational.
     */
    String getStatus();

}
