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

    private static final String FIELD = "q.field";
    private static final String OPER = "q.op";
    private static final String VALUE = "q.value";
    
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
	 
	@Override
	public List<? extends Sample> samples(String meterName) {
		checkNotNull(meterName);

		CeilometerSample[] samples = get(CeilometerSample[].class, uri("/meters/%s", meterName)).execute();
		return wrapList(samples);
	}
	
	/**
     * {@inheritDoc}
     
    @Override
    public List<? extends Sample> samples(String meterName, SampleCriteria criteria) {
        checkNotNull(meterName);

        Invocation<CeilometerSample[]> invocation = get(CeilometerSample[].class, uri("/meters/%s", meterName));
        if (criteria != null && !criteria.getCriteriaParams().isEmpty()) {
            for (NameOpValue c : criteria.getCriteriaParams()) {
                invocation.param(FIELD, c.getField());
                invocation.param(OPER, c.getOperator().getQueryValue());
                invocation.param(VALUE, c.getValue());
            }
        }
        
        CeilometerSample[] samples = invocation.execute();
        return wrapList(samples);
    }

	/**
	 * {@inheritDoc}
	 
	@Override
	public List<? extends Statistics> statistics(String meterName) {
		return statistics(meterName, 0);
	}

	@Override
	public List<? extends Statistics> statistics(String meterName, int period) {
checkNotNull(meterName);
		
		CeilometerStatistics[] stats = get(CeilometerStatistics[].class, uri("/meters/%s/statistics", meterName))
																	  .param(period > 0, "period", period)
																		.execute();
		return wrapList(stats);
	}
	*/
	
	@Override
	public Alarm getById(String alarmId){
		checkNotNull(alarmId);
        
        return get(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).execute();
		
	}
	
	@Override
	public void update(String alarmId, Alarm alarm){
		checkNotNull(alarmId);
		checkNotNull(alarm);
		
		put(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).entity(alarm).execute();
	}

}
