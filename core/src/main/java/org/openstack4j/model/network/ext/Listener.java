package org.openstack4j.model.network.ext;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.ListenerBuilder;
import org.openstack4j.openstack.networking.domain.ext.ListItem;
import org.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;

import java.util.List;

/**
 * A listener for v2 loadbalancer
 * @author emjburns
 */
@JsonDeserialize(as = NeutronListenerV2.class)
public interface Listener extends ModelEntity, Buildable<ListenerBuilder> {

    /**
     * @return id. The unique ID for the listener.
     */
    String getId();

    /**
     * @return tenantId.
     * Owner of the listener. Only an administrative user can specify a tenant ID other than its own.
     */
    String getTenantId();

    /**
     * @return listener name. Does not have to be unique.
     */
    String getName();

    /**
     * @return Description for the listener.
     */
    String getDescription();

    /**
     * @return The protocol of the listener, which is TCP, HTTP, or HTTPS.
     */
    Protocol getProtocol();

    /**
     * @return The protocol of the listener.
     */
    Integer getProtocolPort();

    /**
     * @return The connection limit of the listener.
     * -1 indicates an infinite limit.
     */
    Integer getConnectionLimit();

    /**
     * @return The default pool id of the listener.
     */
    String getDefaultPoolId();

    /**
     * @return The administrative state of the listener, which is up (true) or
     *         down (false).
     */
    boolean isAdminStateUp();

    /**
     * @return The loadbalancers of the listener.
     */
    List<ListItem> getLoadBalancers();
}
