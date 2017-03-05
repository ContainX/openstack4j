package org.openstack4j.api.telemetry;

import org.openstack4j.openstack.telemetry.domain.GnocchiMetricsMetadata;

import java.util.List;

/**
 * Created by eandgya on 3/3/17.
 */
public interface MetricGnocchiService extends MeterService {
    public List<GnocchiMetricsMetadata> metricsMetadata();
}
