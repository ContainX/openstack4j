package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.ListenerV2UpdateBuilder;

/**
 * An entity used to update an lbaas v2 listener
 *
 * @author emjburns
 */
public interface ListenerV2Update extends ModelEntity, Buildable<ListenerV2UpdateBuilder> {
    /**
     * Optional
     *
     * @see ListenerV2#isAdminStateUp()
     */
    boolean isAdminStateUp();

    /**
     * Optional
     *
     * @see ListenerV2#getDescription()
     */
    String getDescription();

    /**
     * Optional
     *
     * @see ListenerV2#getName()
     */
    String getName();

    /**
     * Optional
     *
     * @see ListenerV2#getConnectionLimit()
     */
    Integer getConnectionLimit();

    /**
     * Optional
     *
     * @see ListenerV2#getDefaultTlsContainerRef()
     */
    String getDefaultTlsContainerRef();
}
