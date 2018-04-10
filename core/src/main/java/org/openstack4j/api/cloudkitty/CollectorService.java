package org.openstack4j.api.cloudkitty;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.CollectorInfos;
import org.openstack4j.model.cloudkitty.ServiceToCollectorMapping;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * Cloudkitty v1 Collector API
 *
 * @author mariusleu
 */
public interface CollectorService extends RestService {

    /**
     * List the service to collector mappings
     *
     * @return the list of service to collector mappings
     */
    List<? extends ServiceToCollectorMapping> list();

    /**
     * Get a service to collector mapping
     *
     * @param service the service name
     * @return the service to collector mapping
     */
    ServiceToCollectorMapping get(String service);

    /**
     * Create a service to collector mapping
     *
     * @param collector the collector name
     * @param service the service name
     * @return the newly created service to collector mapping
     */
    ServiceToCollectorMapping create(String collector, String service);

    /**
     * Delte a service to collector mapping
     *
     * @param service service name
     * @return action response
     */
    ActionResponse delete(String service);

    /**
     * Set the enable state of a collector
     *
     * @param collector collector name
     * @param infos new state informations of the collector
     * @return State of the collector
     */
    CollectorInfos setState(String collector, CollectorInfos infos);

}
