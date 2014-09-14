package org.openstack4j.model.compute.ext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openstack4j.model.ModelEntity;

/**
 * list all AvailabilityZones
 * 
 * @author octopus zhang
 */
public interface AvailabilityZones extends ModelEntity{
	
	/**
	 * @return AvailabilityZone list
	 */
	List<? extends AvailabilityZone> getAvailabilityZoneList();


	public interface AvailabilityZone extends ModelEntity{
		
		/**
		 * @return zone's state
		 */
		ZoneState getZoneState();
		
		/**
		 * @return hosts in the zone and nova services in each host, not really implemented yet
		 */
		HashMap<String, HashMap<String, ? extends NovaService> > getHosts();
		
		/**
		 * @return zone's name
		 */
		String getZoneName();
	}
	
	public interface ZoneState extends ModelEntity{
		/**
		 * @return the state of zone
		 */
		boolean getAvailable();
	}

	public interface NovaService extends ModelEntity{
		/**
		 * @return whether the service is avaiable 
		 */
		String getAvailable();
		
		/**
		 * @return the service's status
		 */
		String getStatusActive();
		
		/**
		 * @return the service's latest update time
		 */
		Date getUpdateTime();
	}
}
