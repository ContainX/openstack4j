package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.telemetry.MeterService;
import org.openstack4j.api.telemetry.MetricGnocchiService;
import org.openstack4j.model.telemetry.Meter;
import org.openstack4j.model.telemetry.MeterSample;
import org.openstack4j.model.telemetry.SampleCriteria;
import org.openstack4j.model.telemetry.Statistics;
import org.openstack4j.openstack.telemetry.domain.GnocchiMetricsMetadata;

import java.util.List;

/**
 * Created by eandgya on 3/3/17.
 */
public class MetricGnocchiServiceImpl extends BaseTelemetryGnocchiServices implements MetricGnocchiService {
    @Override
    public List<? extends Meter> list() {
        return null;
    }

    @Override
    public List<? extends MeterSample> samples(String meterName) {
        return null;
    }

    @Override
    public List<? extends MeterSample> samples(String meterName, SampleCriteria criteria) {
        return null;
    }

    @Override
    public List<? extends Statistics> statistics(String meterName) {
        return null;
    }

    @Override
    public List<? extends Statistics> statistics(String meterName, SampleCriteria criteria) {
        return null;
    }

    @Override
    public List<? extends Statistics> statistics(String meterName, SampleCriteria criteria, int period) {
        return null;
    }

    @Override
    public List<? extends Statistics> statistics(String meterName, int period) {
        return null;
    }

    @Override
    public void putSamples(List<MeterSample> sampleList, String meterName) {

    }
    @Override
    public List<GnocchiMetricsMetadata> metricsMetadata() {
        GnocchiMetricsMetadata[] meters = get(GnocchiMetricsMetadata[].class, uri("/v1/metric")).execute();
        return wrapList(meters);
    }
}
