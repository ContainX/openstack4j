package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPair;

/**
 *
 * Service Port Pair Service
 *
 * @author Dmitry Gerenrot
 *
 */
public interface PortPairService {

    /**
     * Lists Port Pairs for port chains
     *
     * @return the list of Port Pairs
     */
    List<? extends PortPair> list();


    /**
     * Get a Port Pair by id.
     * @param portPairId
     * @return PortPair
     */
    PortPair get(String portPairId);

    /**
     * Update a Port Pair with the given id to match the given update object
     *
     * @param portPairId
     * @param portPair
     * @return PortPair
     */
    PortPair update(String portPairId, PortPair portPair);

    /**
     * Create a Port Pair
     *
     * @param portPair
     * @return PortPair : object actually created
     */
    PortPair create(PortPair portPair);

    /**
     * Delete a Port Pair
     *
     * @param portPairId
     * @return the action response
     */
    ActionResponse delete(String portPairId);
}
