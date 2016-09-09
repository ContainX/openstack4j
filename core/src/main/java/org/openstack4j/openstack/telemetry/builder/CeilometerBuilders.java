package org.openstack4j.openstack.telemetry.builder;


import org.openstack4j.model.telemetry.builder.*;
import org.openstack4j.openstack.telemetry.domain.*;

/**
 * The Ceilometer V3 Builders
 */
public class CeilometerBuilders implements TelemetryBuilders {

    @Override
    public AlarmBuilder alarm() {
        return CeilometerAlarm.builder();
    }
}
