package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.network.ext.ListenerUpdate;

/**
 * A Builder to update a lbaas v2 listener
 * @author emjburns
 */
public interface ListenerUpdateBuilder extends Buildable.Builder<ListenerUpdateBuilder, ListenerUpdate> {

    /**
     * Optional
     *
     * @param name
     *            Human-readable name for the listener. Does not have to be unique.
     * @return ListenerUpdateBuilder
     */
    ListenerUpdateBuilder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Human-readable description for the listener.
     * @return ListenerUpdateBuilder
     */
    ListenerUpdateBuilder description(String description);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the listener. A valid value is true
     *            (UP) or false (DOWN).
     * @return ListenerUpdateBuilder
     */
    ListenerUpdateBuilder adminStateUp(boolean adminStateUp);

    /**
     * Optional
     * The maximum number of connections allowed for the listener. Default is -1, meaning no limit.
     * @param connectionLimit
     * @return ListenerUpdate Builder
     */
    ListenerUpdateBuilder connectionLimit(Integer connectionLimit);
}
