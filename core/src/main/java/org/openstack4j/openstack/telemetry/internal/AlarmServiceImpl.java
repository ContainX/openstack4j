package org.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.telemetry.AlarmService;
import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.model.telemetry.Alarm;
import org.openstack4j.model.telemetry.Meter;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.SampleCriteria;
import org.openstack4j.model.telemetry.SampleCriteria.NameOpValue;
import org.openstack4j.model.telemetry.Statistics;
import org.openstack4j.openstack.heat.domain.HeatStack;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm;
import org.openstack4j.openstack.telemetry.domain.CeilometerMeter;
import org.openstack4j.openstack.telemetry.domain.CeilometerSample;
import org.openstack4j.openstack.telemetry.domain.CeilometerStatistics;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 * 
 * @author Massimiliano Romano
 */
public class AlarmServiceImpl extends BaseTelemetryServices implements AlarmService {

    
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Alarm> list() {
		CeilometerAlarm[] alarms = get(CeilometerAlarm[].class, uri("/alarms")).execute();
		//CeilometerMeter[] meters = get(CeilometerMeter[].class, uri("/meters")).execute();
		return wrapList(alarms);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alarm getById(String alarmId){
		checkNotNull(alarmId);
        
        return get(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).execute();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(String alarmId, Alarm alarm){
		checkNotNull(alarmId);
		checkNotNull(alarm);
		
		put(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).entity(alarm).execute();
	}

}
