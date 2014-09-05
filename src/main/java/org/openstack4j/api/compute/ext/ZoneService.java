package org.openstack4j.api.compute.ext;


import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ext.AvailabilityZoneInfo;
/**
 * API which supports the "os-availability-zone" extension.  
 * 
 * @author octopus zhang
 */
public interface ZoneService extends RestService{
	
	/**
	 *  List availability zone info .
	 *  
	 *  NOTE: This is an extension and not all deployments support os-availability-zone
	 *  
	 * @return the available zones in detail
	 */
	AvailabilityZoneInfo getAvailabilityZoneInfo();
}
