package org.openstack4j.api.bareMetal;

import org.openstack4j.model.bareMetal.Port;
import org.openstack4j.model.bareMetal.PortUpdate;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * bare metal ports service
 *
 * @author zhangliang
 */
public interface PortService {

    /**
     * List Ports
     * @return List of Port
     */
    List<? extends Port> list();

    /**
     * Create Port
     * @param port
     * @return Port
     */
    Port create(Port port);

    /**
     * Delete Port
     * @param portId
     * @return ActionResponse
     */
    ActionResponse delete(String portId);

    /**
     * Update a Port
     * @param portId
     * @param portUpdate
     * @return Port
     */
    Port update(String portId, PortUpdate portUpdate);

}
