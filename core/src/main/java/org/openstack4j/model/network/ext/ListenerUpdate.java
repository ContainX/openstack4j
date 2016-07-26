package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.ListenerUpdateBuilder;

/**
 * An entity used to update an lbaas v2 listener
 * @author emjburns
 */
public interface ListenerUpdate extends ModelEntity, Buildable<ListenerUpdateBuilder> {
    /**
     * Optional
     * @see Listener#isAdminStateUp()
     */
    public boolean isAdminStateUp();

    /**
     * Optional
     * @see Listener#getDescription()
     */
    public String getDescription();

    /**
     * Optional
     * @see Listener#getName()
     */
    public String getName();

    /**
     * Optional
     * @see Listener#getConnectionLimit()
     */
    public Integer getConnectionLimit();

}
