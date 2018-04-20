package org.openstack4j.api.bareMetal;

import org.openstack4j.model.bareMetal.Node;
import org.openstack4j.model.bareMetal.NodeManagement;
import org.openstack4j.model.bareMetal.NodeUpdate;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

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

//    ActionResponse start(String nodeIdent);
//
//    ActionResponse stop(String nodeIdent);
//
//    ActionResponse reboot(String nodeIdent);

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

}
