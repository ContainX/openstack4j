package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.telemetry.*;
import org.openstack4j.openstack.telemetry.domain.GnocchiMetricsMetadata;

/**
 * Created by eandgya on 3/3/17.
 */
public class TelemetryGnocchiServiceImpl implements TelemetryGnocchiService {
    @Override
    public MetricGnocchiService meters() {
        return Apis.get(MetricGnocchiService.class);
    }


    @Override
    public AlarmService alarms() {
        return null;
    }

    @Override
    public EventService events() {
        return null;
    }

    @Override
    public SampleService samples() {
        return null;
    }

    @Override
    public ResourceService resources() {
        return null;
    }

    @Override
    public CapabilitiesService capabilities() {
        return null;
    }
}
