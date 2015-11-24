package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.telemetry.EventService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.AlarmService;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AlarmService alarms() {
		return Apis.get(AlarmService.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EventService events() {
		return Apis.get(EventService.class);
	}

}
