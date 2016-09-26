package org.openstack4j.openstack.murano.v1.internal;


import org.openstack4j.api.Apis;
import org.openstack4j.api.murano.v1.MuranoEnvironmentService;
import org.openstack4j.api.murano.v1.AppCatalogService;

/**
 * This class contains getters for all implementation of the available Murano services
 *
 * @author Nikolay Mahotkin
 */
public class MuranoService extends BaseMuranoServices implements AppCatalogService {

    @Override
    public MuranoEnvironmentService environments() {
        return Apis.get(MuranoEnvironmentService.class);
    }
}
