package org.openstack4j.model.gbp;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyActionBuilder;

/**
 * Policy Action Model Entity
 * 
 * @author vinod borole
 */
public interface PolicyAction extends Buildable<PolicyActionBuilder>, Resource {

    /**
     * Gets the Action value
     *
     * @return the Action value
     */
    String getActionValue();

    /**
     * Gets the Action Type
     *
     * @return the Action Type
     */
    String getActionType();

    /**
     * Is Policy Action shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

}
  