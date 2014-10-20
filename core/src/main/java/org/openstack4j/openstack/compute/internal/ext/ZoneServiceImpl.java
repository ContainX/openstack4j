package org.openstack4j.openstack.compute.internal.ext;

import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.model.compute.ext.AvailabilityZones;
import org.openstack4j.openstack.compute.domain.ext.ExtAvailabilityZones;
import org.openstack4j.openstack.compute.internal.BaseComputeServices;

public class ZoneServiceImpl extends BaseComputeServices implements ZoneService {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AvailabilityZones getAvailabilityZones() {
		return get(ExtAvailabilityZones.class, "/os-availability-zone").execute();
	}

}
