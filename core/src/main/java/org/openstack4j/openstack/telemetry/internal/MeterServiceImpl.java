package org.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.model.telemetry.Meter;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.Statistics;
import org.openstack4j.openstack.telemetry.domain.CeilometerMeter;
import org.openstack4j.openstack.telemetry.domain.CeilometerSample;
import org.openstack4j.openstack.telemetry.domain.CeilometerStatistics;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 * 
 * @author Jeremy Unruh
 */
public class MeterServiceImpl extends BaseTelemetryServices implements MeterService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Meter> list() {
		CeilometerMeter[] meters = get(CeilometerMeter[].class, uri("/meters")).execute();
		return wrapList(meters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Sample> samples(String meterName) {
		checkNotNull(meterName);

		CeilometerSample[] samples = get(CeilometerSample[].class, uri("/meters/%s", meterName)).execute();
		return wrapList(samples);
	}

	/**
	 * {@inheritDoc}
	 */
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

}
