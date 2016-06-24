package org.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.telemetry.SampleService;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.openstack.telemetry.domain.CeiloMeterSample;

/**
 * Provides Measurements for Samples within an OpenStack deployment
 * 
 * @author Shital Patil
 */

public class SampleServiceImpl extends BaseTelemetryServices implements SampleService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Sample> list() {
        CeiloMeterSample[] samples = get(CeiloMeterSample[].class, uri("/samples")).execute();
        return wrapList(samples);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sample get(String sampleId) {
        checkNotNull(sampleId);

        CeiloMeterSample sample = get(CeiloMeterSample.class, uri("/samples/%s", sampleId)).execute();
        return sample;
    }

}
