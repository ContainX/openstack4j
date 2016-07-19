package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.network.ext.LbMethod;
import org.openstack4j.model.network.ext.LbPoolV2;
import org.openstack4j.model.network.ext.Protocol;
import org.openstack4j.model.network.ext.SessionPersistence;

/**
 * A Builder to create a lbpoolV2
 *
 * @author emjburns
 *
 */
public interface LbPoolV2Builder extends Buildable.Builder<LbPoolV2Builder, LbPoolV2> {
    /**
     * @param tenantId
     *            Owner of the pool. Only an administrative user can specify a
     *            tenant ID other than its own.
     * @return LbPoolBuilder
     */
    LbPoolV2Builder tenantId(String tenantId);

    /**
     * Optional
     *
     * @param name
     *            Pool name. Does not have to be unique.
     * @return LbPoolBuilder
     */
    LbPoolV2Builder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Description for the pool.
     * @return LbPoolBuilder
     */
    LbPoolV2Builder description(String description);

    /**
     * @param protocol
     *            The protocol of the VIP address. A valid value is TCP, HTTP,
     *            or HTTPS.
     * @return LbPoolBuilder
     */
    LbPoolV2Builder protocol(Protocol protocol);

    /**
     * @param lbMethod
     *            The load-balancer algorithm, which is round-robin,
     *            least-connections, and so on. This value, which must be
     *            supported, is dependent on the load-balancer provider. Round
     *            robin must be supported.
     *            Must be one of ROUND_ROBIN, LEAST_CONNECTIONS, or SOURCE_IP.
     * @return LbPoolBuilder
     */
    LbPoolBuilder lbMethod(LbMethod lbMethod);

    /**
     * Optional
     *
     * @param sessionPersistence
     *            Default value empty dictionary
     * @return VipBuilder
     */
    LbPoolV2Builder sessionPersistence(SessionPersistence sessionPersistence);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the lb pool, which is up (true) or
     *            down (false). Default value true.
     * @return LbPoolBuilder
     */
    LbPoolV2Builder adminStateUp(boolean adminStateUp);

    /**
     * The listener in which this pool will become the default pool.
     * There can only be on default pool for a listener.
     * @param listenerId
     * @return
     */
    LbPoolV2Builder listenerId(String listenerId);
}
