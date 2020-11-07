package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.ext.NetworkIPAvailabilityService;
import org.openstack4j.model.network.NetworkIPAvailability;
import org.openstack4j.model.network.SubnetIpAvailability;
import org.openstack4j.model.network.options.NetIpAvailListOptions;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetworkIPAvailability;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetworkIPAvailability.NetworkIPAvailabilities;
import org.openstack4j.openstack.networking.domain.ext.NeutronSubnetIpAvailability;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author bboyHan
 */
public class NetworkIPAvailabilityServiceImpl extends BaseNetworkingServices implements NetworkIPAvailabilityService {

    @Override
    public List<? extends NetworkIPAvailability> list() {
        return get(NetworkIPAvailabilities.class, uri("/network-ip-availabilities")).execute().getList();
    }

    @Override
    public List<? extends NetworkIPAvailability> list(NetIpAvailListOptions options) {
        if (options == null)
            return list();

        return get(NetworkIPAvailabilities.class, uri("/network-ip-availabilities")).paramLists(options.getRepeatableOptions())
                .execute().getList();
    }

    @Override
    public NetworkIPAvailability get(String networkId) {
        checkNotNull(networkId);
        return get(NeutronNetworkIPAvailability.class, uri("/network-ip-availabilities/%s", networkId)).execute();
    }

    @Override
    @SuppressWarnings("unchecked")
    public SubnetIpAvailability getSubnetIpAvail(String subnetId) {
        String networkId = Apis.getNetworkingServices().subnet().get(subnetId).getNetworkId();
        List<NeutronSubnetIpAvailability> subnetIpAvailabilities = (List<NeutronSubnetIpAvailability>) get(networkId).getSubnetIpAvailabilities();
        for (NeutronSubnetIpAvailability subnetIpAvailability : subnetIpAvailabilities) {
            if (subnetIpAvailability.getSubnetId().equalsIgnoreCase(subnetId)) {
                return subnetIpAvailability;
            }
        }
        return null;
    }
}
