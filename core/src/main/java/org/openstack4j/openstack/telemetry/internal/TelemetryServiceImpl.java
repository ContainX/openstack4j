package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.TelemetryService;

/**
 * Telemetry allows collection of Alarms and Measurements
 * 
 * @author Jeremy Unruh
 */
public class TelemetryServiceImpl implements TelemetryService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MeterService meters() {
		return Apis.get(MeterService.class);
	}

}
