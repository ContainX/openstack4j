package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.network.NetQuota;

/**
 * Networking (Neutron) Quota Extension API
 * 
 * @author Jeremy Unruh
 */
public interface NetQuotaService extends RestService {
    
    /**
     * Fetches the network quotas for the current tenant
     * 
     * @return the tenants quota
     */
    NetQuota get();

}
