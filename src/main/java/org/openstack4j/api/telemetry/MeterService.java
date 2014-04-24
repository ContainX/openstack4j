package org.openstack4j.api.telemetry;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.telemetry.Meter;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.Statistics;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 * 
 * @author Jeremy Unruh
 */
public interface MeterService extends RestService {

	/**
	 * Return all known meters, based on the data recorded so far.
	 * 
	 * @return list of all known meters
	 */
	List<? extends Meter> list();
	
	/**
	 * Returns samples for the specified Meter
	 * @param meterName the name of the meter to fetch samples from
	 * 
	 * @return List of Samples
	 */
	List<? extends Sample> samples(String meterName);
	
	/**
	 * Returns computed statistics for the given meterName
	 * @param meterName the name of the meter to fetch statistics for
	 * 
	 * @return List of Statistics
	 */
	List<? extends Statistics> statistics(String meterName);
	
	/**
	 * Returns computed statistics for the given meterName for the given time range
	 * @param meterName the name of the meter to fetch statistics for
	 * @param period the result will be statistics for a period long of that number of seconds
	 * @return List of Statistics
	 */
	List<? extends Statistics> statistics(String meterName, int period);
}
