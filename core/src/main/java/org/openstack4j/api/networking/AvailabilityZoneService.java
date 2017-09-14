package org.openstack4j.api.networking;

import java.util.List;
import org.openstack4j.common.RestService;
import org.openstack4j.model.network.AvailabilityZone;
import org.openstack4j.openstack.networking.domain.NeutronAvailabilityZone;

/**
 * Neutron Availability Zone Service
 * 
 * @author Taemin
 *
 */
public interface AvailabilityZoneService extends RestService {


	/**
     * List all availability zone that the current neutron has.
     * 
     * @return list of all availability zones
     * @author Taemin
     */
    List<? extends AvailabilityZone> list();

}
