package org.openstack4j.api.murano.v1;

import org.openstack4j.common.RestService;

/**
 * (Murano) App catalog Operations API
 *
 * @author Nikolay Mahotkin
 */
public interface AppCatalogService extends RestService {

    /**
     * Environments Service API
     *
     * @return the environments service
     */
    MuranoEnvironmentService environments();
}
