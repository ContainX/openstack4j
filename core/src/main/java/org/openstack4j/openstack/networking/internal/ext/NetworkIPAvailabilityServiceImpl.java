package org.openstack4j.openstack.networking.internal.ext;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.ext.NetworkIPAvailabilityService;
import org.openstack4j.model.network.ext.NetworkIPAvailability;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetworkIPAvailability;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetworkIPAvailability.NeutronNetworkIPAvailabilities;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Network IP availability and usage stats Extension API
 * 
 * @author Xiangbin HAN
 */
public class NetworkIPAvailabilityServiceImpl extends BaseNetworkingServices implements NetworkIPAvailabilityService {

    @Override
    public List<? extends NetworkIPAvailability> get() {
        return get(NeutronNetworkIPAvailabilities.class, uri("/network-ip-availabilities")).execute().getList();
    }

    @Override
    public NetworkIPAvailability get(String networkId) {
        checkNotNull(networkId, "NetworkId must not be null");
        return get(NeutronNetworkIPAvailability.class, uri("/network-ip-availabilities/%s", networkId)).execute();
    }

}
