package org.openstack4j.api.telemetry;

/**
 * Created by eandgya on 3/3/17.
 */
public interface TelemetryGnocchiService extends TelemetryService {
    @Override
    MetricGnocchiService meters();
}
