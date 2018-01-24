package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPairGroup;

/**
 * Port Pair Group Service
 *
 * @author Dmitry Gerenrot
 *
 */
public interface PortPairGroupService {

    /**
     * Lists port pair groups for port chains
     *
     * @return the list of port pair groups
     */
    List<? extends PortPairGroup> list();

    /**
     * Get a port pair group by id.
     * @param id
     * @return PortPairGroup
     */
    PortPairGroup get(String id);

    /**
     * Update a port pair group with the given id to match the given update object
     *
     * @param id
     * @param portPairGroup
     * @return PortPairGroup
     */
    PortPairGroup update(String portPairGroupId, PortPairGroup portPairGroup);

    /**
     * Create a port pair group
     *
     * @param portPairGroup
     * @return portPairGroup : object actually created
     */
    PortPairGroup create(PortPairGroup portPairGroup);

    /**
     * Delete a port pair group
     *
     * @param portPairGroupId
     * @return the action response
     */
    ActionResponse delete(String portPairGroupId);
}
