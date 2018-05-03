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

    /**
     * The boot device for a Node, eg. “pxe” or “disk”.
     * @param bootDevice
     * @return
     */
    NodeManagementBuilder bootDevice(String bootDevice);

    /**
     * Whether the boot device should be set only for the next reboot, or persistently.
     * @param persistent
     * @return
     */
    NodeManagementBuilder persistent(Boolean persistent);

    /**
     * Indicates whether console access is enabled or disabled on this node.
     * @param enabled
     * @return
     */
    NodeManagementBuilder enabled(Boolean enabled);

}
