package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.builder.VipUpdateBuilder;

/**
 * An entity used to update a vip
 *
 * @author liujunpeng
 */
public interface VipUpdate extends ModelEntity, Buildable<VipUpdateBuilder> {
    /**
     * Optional
     *
     * @see Vip#isAdminStateUp()
     */
    boolean isAdminStateUp();

    /**
     * Optional
     *
     * @see Vip#getConnectionLimit()
     */
    Integer getConnectionLimit();

    /**
     * Optional
     *
     * @see Vip#getDescription()
     */
    String getDescription();

    /**
     * Optional
     *
     * @see Vip#getName()
     */
    String getName();

    /**
     * Optional
     *
     * @see Vip#getPoolId()
     */
    String getPoolId();

    /**
     * Optional
     *
     * @see Vip#getSessionPersistence()
     */
    SessionPersistence getSessionPersistence();

}
