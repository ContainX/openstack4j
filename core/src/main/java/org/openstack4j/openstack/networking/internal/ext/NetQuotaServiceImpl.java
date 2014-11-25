package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.NetQuotaService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.NetQuota;
import org.openstack4j.openstack.networking.domain.NeutronNetQuota;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import com.google.common.base.Preconditions;

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

    @Override
    public NetQuota update(NetQuota netQuota) {
        Preconditions.checkNotNull(netQuota, "NetQuota must not be null");
        return put(NeutronNetQuota.class, uri("/quotas")).entity(netQuota).execute();
    }

    @Override
    public ActionResponse reset() {
        return deleteWithResponse(uri("/quotas")).execute();
    }

}
