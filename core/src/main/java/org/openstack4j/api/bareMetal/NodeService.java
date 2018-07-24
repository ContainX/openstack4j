package org.openstack4j.api.bareMetal;

import org.openstack4j.model.bareMetal.*;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;
import java.util.Map;

/**
 * bare metal nodes service
 *
 * @author zhangliang
 */
public interface NodeService {

    /**
     * List Nodes
     * @return List of Node
     */
    List<? extends Node> list();

    /**
     * List Nodes with http headers
     * @param headers
     * @return
     */
    List<? extends Node> list(Map<String, Object> headers);

    /**
     * Create Node
     * @param node
     * @return Node
     */
    Node create(Node node);

    /**
     * Show Node Details
     * @param nodeIdent
     * @return Node
     */
    Node findOne(String nodeIdent);

    /**
     * Show Node Details with http headers
     * @param nodeIdent
     * @param headers
     * @return
     */
    Node findOne(String nodeIdent, Map<String, Object> headers);

    /**
     * Delete Node
     * @param nodeIdent
     * @return ActionResponse
     */
    ActionResponse delete(String nodeIdent);

    /**
     * Update Node
     * @param nodeIdent
     * @param nodeUpdate
     * @return Node
     */
    Node update(String nodeIdent, NodeUpdate nodeUpdate);

    /**
     * Change Node Power State
     * @param nodeIdent
     * @param nodeManagement
     * @return ActionResponse
     */
    ActionResponse changePowerState(String nodeIdent, NodeManagement nodeManagement);

    /**
     * Set Maintenance Flag
     * @param nodeIdent
     * @return ActionResponse
     */
    ActionResponse setMaintenance(String nodeIdent);

    /**
     * Clear Maintenance Flag
     * @param nodeIdent
     * @return ActionResponse
     */
    ActionResponse clearMaintenance(String nodeIdent);

    /**
     * Set the boot device for the given Node, and set it persistently or for one-time boot.
     * @param nodeIdent
     * @param nodeManagement
     * @return
     */
    ActionResponse setBootDevice(String nodeIdent, NodeManagement nodeManagement);

    /**
     * Start or stop the serial console.
     * @param nodeIdent
     * @param nodeManagement
     * @return
     */
    ActionResponse setConsole(String nodeIdent, NodeManagement nodeManagement);

    /**
     * Change Node Provision State
     * @param nodeIdent
     * @param nodeManagement
     * @return
     */
    ActionResponse setProvision(String nodeIdent, NodeManagement nodeManagement);

    /**
     * Change Node Provision with Headers
     * @param nodeIdent
     * @param nodeManagement
     * @param headers
     * @return
     */
    ActionResponse setProvision(String nodeIdent, NodeManagement nodeManagement, Map<String, Object> headers);

    /**
     * Validate Node
     * @param nodeIdent
     * @return
     */
    NodeValidate validate(String nodeIdent);

    /**
     * Get Supported Boot Devices
     * @param nodeIdent
     * @return
     */
    NodeSupportedBootDevices getSupportedBootDevices(String nodeIdent);

    /**
     * Get Boot Device
     * @param nodeIdent
     * @return
     */
    BootDevice getBootDevice(String nodeIdent);

}
