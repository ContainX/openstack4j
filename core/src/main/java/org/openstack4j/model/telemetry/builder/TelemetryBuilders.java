package org.openstack4j.model.telemetry.builder;

import org.openstack4j.model.telemetry.Alarm;

/**
 * The Ceilometer builders
 */
public interface TelemetryBuilders {

    /**
     * The builder to create an Alarm
     *
     * @return the image builder
     */
    public AlarmBuilder alarm();

}
