package org.openstack4j.openstack.networking.internal;

import java.util.List;

import org.openstack4j.api.networking.AvailabilityZoneService;
import org.openstack4j.model.network.AvailabilityZone;
import org.openstack4j.openstack.networking.domain.NeutronAvailabilityZone.AvailabilityZones;


/**
 * Availability Zone Service
 * 
 * @author Taemin
 *
 */
public class AvailabilityZoneServiceImpl extends BaseNetworkingServices implements AvailabilityZoneService {

    /**
     * List all availability zone that the current neutron has
     * {@inheritDoc}
     * @author Taemin
     */
	@Override
	public List<? extends AvailabilityZone> list() {		
		return get(AvailabilityZones.class, uri("/availability_zones")).execute().getList();	
	}

   
}
