package org.openstack4j.openstack.compute.internal.ext;

import org.openstack4j.api.compute.ext.ZoneService;
import org.openstack4j.model.compute.ext.AvailabilityZoneInfo;
import org.openstack4j.openstack.compute.domain.ext.ExtAvailabilityZoneInfo;
import org.openstack4j.openstack.compute.internal.BaseComputeServices;

public class ZoneServiceImpl extends BaseComputeServices implements ZoneService {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AvailabilityZoneInfo getAvailabilityZoneInfo() {
		return get(ExtAvailabilityZoneInfo.class, "/os-availability-zone").execute();
	}

}
