package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.network.NetworkIPAvailability;
import org.openstack4j.model.network.SubnetIpAvailability;
import org.openstack4j.model.network.options.NetIpAvailListOptions;

import java.util.List;

/**
 * Networking (Neutron) ip-availabilities Extension API
 *
 * @author bboyHan
 */
public interface NetworkIPAvailabilityService extends RestService {

    List<? extends NetworkIPAvailability> list();

    List<? extends NetworkIPAvailability> list(NetIpAvailListOptions options);

    NetworkIPAvailability get(String networkId);

    /**
     * extension about subnet
     *
     * @param subnetId subnet id
     */
    SubnetIpAvailability getSubnetIpAvail(String subnetId);
}
