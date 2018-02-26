package org.openstack4j.api.cloudkitty;

import org.openstack4j.common.RestService;
import org.openstack4j.model.cloudkitty.ServiceInfo;

import java.util.List;
import java.util.Map;

/**
 * A collector info service
 * @author mariusleu
 */
public interface InfoService extends RestService {

    /**
     * @return the current configuration
     */
    Map<String, String> config();

    /**
     * @return List of every services
     */
    List<ServiceInfo> services();

    /**
     * @param service service name
     * @return a service
     */
    ServiceInfo service(String service);
}
