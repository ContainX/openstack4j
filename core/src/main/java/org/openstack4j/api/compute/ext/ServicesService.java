package org.openstack4j.api.compute.ext;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.ext.Service;
import org.openstack4j.openstack.compute.domain.ext.ExtService;

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
    
    /**
     * Returns list of compute services filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return 
     * @author Wang Ting/王婷
     */
    List<? extends Service> list(Map<String, String> filteringParams);

    /**
     * Enables a compute services.
     *
     * @param binary the name of the service binary that you want to enable
     * @param host the host name of the service that you want to enable
     * @return the enabled service
     * 
     * @author Wang Ting/王婷
     */
    ExtService enableService(String binary, String host);

    /**
     * Disables a service.
     *
     * @param binary the name of the service binary that you want to disable
     * @param host the host name of the service that you want to disable
     * @return the disabled service
     * 
     * @author Wang Ting/王婷
     */
    ExtService disableService(String binary, String host);

}