package org.openstack4j.model.gbp;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.gbp.builder.PolicyClassifierUpdateBuilder;

public interface PolicyClassifierUpdate extends ModelEntity, Buildable<PolicyClassifierUpdateBuilder>{

    /**
     * Is Policy classifier shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the Protocol
     *
     * @return the Protocol
     */
    Protocol getProtocol();

    /**
     * Gets the Direction
     *
     * @return the Direction
     */
    Direction getDirection();

    /**
     * Gets the Port range
     *
     * @return the Port range
     */
    String getPortRange();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();
} 
