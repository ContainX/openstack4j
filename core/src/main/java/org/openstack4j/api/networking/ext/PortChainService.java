package org.openstack4j.api.networking.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortChain;

/**
* Port Chain Service
*
* @author Dmitry Gerenrot
*
*/
public interface PortChainService extends RestService {

    /**
     * Lists port chains.
     *
     * @return the list of port chains
     */
    List<? extends PortChain> list();


    /**
     * Get a port chain by id.
     *
     * @param portChainId
     * @return PortChain
     */
    PortChain get(String portChainId);

    /**
     * Update a port chain with the given id to match the given update object
     *
     * @param portChainId
     * @param portChain
     * @return PortChain
     */
    PortChain update(String portChainId, PortChain portChain);

    /**
     * Create a port chain
     *
     * @param portChain
     * @return portChain : object actually created
     */
    PortChain create(PortChain portChain);

    /**
     * Delete a port chain
     *
     * @param portChainId
     * @return the action response
     */
    ActionResponse delete(String portChainId);
}
