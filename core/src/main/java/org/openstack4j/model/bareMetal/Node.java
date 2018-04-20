package org.openstack4j.model.bareMetal;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.builder.NodeBuilder;
import org.openstack4j.model.common.Resource;

import java.util.Map;

/**
 * Bare Metal Node
 *
 * @author zhangliang
 */
public interface Node extends Resource, Buildable<NodeBuilder> {

    /**
     * The name of the driver.
     * @return
     */
    String getDriver();

    /**
     * 	The UUID for the resource.
     * @return
     */
    String getUuid();

    /**
     * UUID of the chassis associated with this Node. May be empty or None.
     * @return
     */
    String getChassisUuid();

    /**
     * UUID of the Nova instance associated with this Node.
     * @return
     */
    String getInstanceUuid();

    /**
     * 	The current power state of this Node
     * @return
     */
    String getPowerState();

    /**
     * The current provisioning state of this Node.
     * @return
     */
    String getProvisionState();

    /**
     * Whether or not this Node is currently in “maintenance mode”.
     * @return
     */
    Boolean getMaintenance();

    /**
     * A set of one or more arbitrary metadata key and value pairs.
     * @return
     */
    Map<String, String> getExtra();

    /**
     * Physical characteristics of this Node.
     * @return
     */
    Map<String, Object> getProperties();

    /**
     * All the metadata required by the driver to manage this Node
     * @return
     */
    Map<String, Object> getDriverInfo();

}
