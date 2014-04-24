package org.openstack4j.api.telemetry;

import org.openstack4j.common.RestService;

/**
 * Telemetry allows collection of Alarms and Measurements. 
 * 
 * @author Jeremy Unruh
 */
public interface TelemetryService extends RestService {
	
	 /**
	  * Access to Metering (Measurements) API
	  * 
	  * @return the Meter Service API
	  */
   MeterService meters();
	
}
