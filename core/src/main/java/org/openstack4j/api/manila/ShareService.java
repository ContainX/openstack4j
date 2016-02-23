package org.openstack4j.api.manila;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.Limits;
import org.openstack4j.model.manila.Service;
import org.openstack4j.openstack.manila.domain.ManilaService;

import java.util.List;

/**
 * Shared File Systems API (Manila)
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface ShareService extends RestService {
    /**
     * @return a list of available Shared File Systems API extensions
     */
    List<? extends Extension> listExtensions();

    /**
     * @return the resource limitations allowed for the tenant
     */
    Limits limits();

    /**
     * @return service which provides methods to manage shares
     */
    SharesService shares();

    /**
     * @return service which provides methods to manage security services
     */
    SecurityServiceService securityServices();

    /**
     * @return service which provides methods to manage share networks
     */
    ShareNetworkService shareNetworks();

    /**
     * @return service which provides methods to manage share servers
     */
    ShareServerService shareServers();

    /**
     * @return service which provides methods to manage share instances
     */
    ShareInstanceService shareInstances();

    /**
     * @return service which provides methods to query scheduler stats
     */
    SchedulerStatsService schedulerStats();

    /**
     * Lists all services.
     *
     * @return a list of all services
     */
    List<? extends Service> services();

    /**
     * Enables a service.
     *
     * @param binary the name of the service binary that you want to enable
     * @param host the host name of the service that you want to enable
     * @return the status of the enabled service
     */
    ManilaService.ServiceStatus enableService(String binary, String host);

    /**
     * Disables a service.
     *
     * @param binary the name of the service binary that you want to disable
     * @param host the host name of the service that you want to disable
     * @return the status of the disabled service
     */
    ManilaService.ServiceStatus disableService(String binary, String host);
}
