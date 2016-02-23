package org.openstack4j.api.manila;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.manila.Limits;

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
}
