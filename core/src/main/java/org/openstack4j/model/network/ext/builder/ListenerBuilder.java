package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.network.ext.Listener;
import org.openstack4j.model.network.ext.Protocol;

public interface ListenerBuilder extends Buildable.Builder<ListenerBuilder, Listener> {
    /**
     * @param tenantId
     *            Owner of the listener. Only an administrative user can specify a
     *            tenant ID other than its own.
     * @return ListenerBuilder
     */
    ListenerBuilder tenantId(String tenantId);

    /**
     * @param protocol
     *            The protocol of the VIP address. A valid value is TCP, HTTP,
     *            or HTTPS.
     * @return ListenerBuilder
     */
    ListenerBuilder protocol(Protocol protocol);

    /**
     * The port in which the frontend will be listening. Must be an integer in the range of 1-65535
     * @param protocolPort
     * @return ListenerBuilder
     */
    ListenerBuilder protocolPort(Integer protocolPort);

    /**
     * The load balancer this listener will be provisioned on. A tenant can only create listeners on
     * load balancers authorized by policy (e.g. her own load balancers).
     * @param loadbalancerId
     * @return ListenerBuilder
     */
    ListenerBuilder loadBalancerId(String loadbalancerId);

    /**
     * Optional
     * @param adminStateUp
     *            The administrative state of the VIP. A valid value is true
     *            (UP) or false (DOWN). Default is true
     * @return ListenerBuilder
     */
    ListenerBuilder adminStateUp(boolean adminStateUp);

    /**
     *  Optional
     *
     * @param name
     *            Pool name. Does not have to be unique.
     * @return ListenerBuilder
     */
    ListenerBuilder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Description for the pool.
     * @return ListenerBuilder
     */
    ListenerBuilder description(String description);

    /**
     * Optional
     *
     * The default value for this attribute will be -1, indicating an infinite limit.
     * @param connectionLimit
     * @return ListenerBuilder
     */
    ListenerBuilder connectionLimit(Integer connectionLimit);
}
