package org.openstack4j.model.octavia.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.octavia.ListenerV2Update;

/**
 * A Builder to update a lbaas v2 listener
 * @author wei
 */
public interface ListenerV2UpdateBuilder extends Buildable.Builder<ListenerV2UpdateBuilder, ListenerV2Update> {

    /**
     * Optional
     *
     * @param name
     *            Human-readable name for the listener. Does not have to be unique.
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder name(String name);

    /**
     * Optional
     *
     * @param description
     *            Human-readable description for the listener.
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder description(String description);

    /**
     * Optional
     *
     * @param adminStateUp
     *            The administrative state of the listener. A valid value is true
     *            (UP) or false (DOWN).
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder adminStateUp(boolean adminStateUp);

    /**
     * Optional
     * The maximum number of connections allowed for the listener. Default is -1, meaning no limit.
     * @param connectionLimit
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder connectionLimit(Integer connectionLimit);

    /**
     * Optional
     *
     * The tls container reference
     * @param defaultTlsContainerRef
     * @return ListenerV2UpdateBuilder
     */
    ListenerV2UpdateBuilder defaultTlsContainerRef(String defaultTlsContainerRef);
}
