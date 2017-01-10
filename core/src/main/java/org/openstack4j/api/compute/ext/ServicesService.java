package org.openstack4j.api.compute.ext;

import java.util.List;

import org.openstack4j.model.compute.ext.Service;

/**
 * API which supports the "os-services" extension.
 *
 * @author Stephan Latour
 */
public interface ServicesService {

    /**
     * List services info
     *
     * NOTE: This is an extension and not all deployments support os-services
     *
     * @return a list of nova services
     */
    List<? extends Service> list();

}