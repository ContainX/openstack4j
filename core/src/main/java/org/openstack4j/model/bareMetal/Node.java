package org.openstack4j.model.bareMetal;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.bareMetal.builder.NodeBuilder;
import org.openstack4j.model.common.Resource;

import java.util.Date;
import java.util.Map;

/**
 * Bare Metal Node
 *
 * @author zhangliang
 */
public interface Node extends Resource, Buildable<NodeBuilder> {

    /**
     * The name of the driver.
     *
     * @return
     */
    String getDriver();

    /**
     * The UUID for the resource.
     *
     * @return
     */
    String getUuid();

    /**
     * UUID of the chassis associated with this Node. May be empty or None.
     *
     * @return
     */
    String getChassisUuid();

    /**
     * UUID of the Nova instance associated with this Node.
     *
     * @return
     */
    String getInstanceUuid();

    /**
     * The current power state of this Node
     *
     * @return
     */
    String getPowerState();

    /**
     * If a power state transition has been requested, this field represents the requested state
     *
     * @return
     */
    String getTargetPowerState();

    /**
     * The current provisioning state of this Node.
     *
     * @return
     */
    String getProvisionState();

    /**
     * If a provisioning action has been requested, this field represents the requested (ie, “target”) state.
     *
     * @return
     */
    String getTargetProvisionState();

    /**
     * Whether or not this Node is currently in “maintenance mode”.
     *
     * @return
     */
    Boolean getMaintenance();

    /**
     * User-settable description of the reason why this Node was placed into maintenance mode
     *
     * @return
     */
    String getMaintenanceReason();

    /**
     * The name of an Ironic Conductor host which is holding a lock on this node, if a lock is held. Usually “null”, but this field can be useful for debugging.
     *
     * @return
     */
    String getReservation();

    /**
     * A set of one or more arbitrary metadata key and value pairs.
     *
     * @return
     */
    Map<String, String> getExtra();

    /**
     * Physical characteristics of this Node.
     *
     * @return
     */
    Map<String, Object> getProperties();

    /**
     * All the metadata required by the driver to manage this Node
     *
     * @return
     */
    Map<String, Object> getDriverInfo();

    /**
     * Internal metadata set and stored by the Node’s driver. This field is read-only.
     *
     * @return
     */
    Map<String, Object> getDriverInternalInfo();

    /**
     * Represents the current RAID configuration of the node. Introduced with the cleaning feature.
     *
     * @return
     */
    Map<String, Object> getRaidConfig();

    /**
     * Represents the requested RAID configuration of the node, which will be applied when the Node next transitions through the CLEANING state. Introduced with the cleaning feature.
     *
     * @return
     */
    Map<String, Object> getTargetRaidConfig();

    /**
     * 	Information used to customize the deployed image.
     * @return
     */
    Map<String, Object> getInstanceInfo();

    /**
     * Any error from the most recent (last) transaction that started but failed to finish.
     * @return
     */
    String getLastError();

    String getCleanStep();

    Date getProvisionUpdateAt();

    Date getInspectionStartedAt();

    Date getInspectionFinishedAt();

}
