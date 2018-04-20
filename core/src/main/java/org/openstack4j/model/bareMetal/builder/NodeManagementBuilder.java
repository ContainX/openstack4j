package org.openstack4j.model.bareMetal.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.NodeManagement;

/**
 * A Builder which creates a NodeManangement
 *
 * @author zhangliang
 */
public interface NodeManagementBuilder extends Buildable.Builder<NodeManagementBuilder, NodeManagement>  {

    /**
     * One of the provisioning verbs: manage, provide, inspect, clean, active, rebuild, delete (deleted), abort, adopt, rescue, unrescue.
     * @param target
     * @return
     */
    NodeManagementBuilder target(String target);

}
