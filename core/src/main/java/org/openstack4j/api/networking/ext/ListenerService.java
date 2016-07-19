package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Listener;

import java.util.List;
import java.util.Map;

/**
 * Networking (Neutron) Lbaas V2 listener Extension API
 * @author emjburns
 */
public interface ListenerService extends RestService {
    /**
     * List all listeners that the current tenant has access to
     *
     * @return list of all listeners
     */
    List<? extends Listener> list();

    /**
     * Returns list of listeners filtered by parameters.
     *
     * @param filteringParams
     *            map (name, value) of filtering parameters
     * @return List
     */
    List<? extends Listener> list(Map<String, String> filteringParams);

    /**
     * Get the specified listener by ID
     *
     * @param listenerId
     *            the listener identifier
     * @return the listener or null if not found
     */
    Listener get(String listenerId);

    /**
     * Delete the specified listener by ID
     *
     * @param listenerId
     *            the listener identifier
     * @return the action response
     */
    ActionResponse delete(String listenerId);

    /**
     * Create a listener
     *
     * @param listener
     *            Listener
     * @return Listener
     */
    Listener create(Listener listener);

    /**
     * Update a listener
     *
     * @param listenerId
     *            the listener identifier
     * @param Listener
     *            ListenerUpdate
     * @return Listener
     */
//    TODO: implement update
//    Listener update(String listenerId, ListenerUpdate listener);
}
