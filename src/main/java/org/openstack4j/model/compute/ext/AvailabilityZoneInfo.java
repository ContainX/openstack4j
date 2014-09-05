package org.openstack4j.model.compute.ext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openstack4j.model.ModelEntity;

public interface AvailabilityZoneInfo extends ModelEntity{
	List<? extends AvailabilityZone> getAvailabilityZoneInfo();


	public interface AvailabilityZone extends ModelEntity{
		ZoneState getZoneState();

		HashMap<String, HashMap<String, ? extends NovaService> > getHosts();

		String getZoneName();
	}
	
	public interface ZoneState extends ModelEntity{
		boolean getAvailable();
	}

	public interface NovaService extends ModelEntity{
		String getAvailable();

		String getStatusActive();

		Date getUpdateTime();
	}
}
