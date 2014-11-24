package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.NetQuotaService;
import org.openstack4j.model.network.NetQuota;
import org.openstack4j.openstack.networking.domain.NeutronNetQuota;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) Quota Extension API
 * 
 * @author Jeremy Unruh
 */
public class NetQuotaServiceImpl extends BaseNetworkingServices implements NetQuotaService {

    @Override
    public NetQuota get() {
        return get(NeutronNetQuota.class, uri("/quotas")).execute();
    }

}
