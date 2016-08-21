package org.openstack4j.api.telemetry;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.telemetry.Sample;
import org.openstack4j.model.telemetry.SampleCriteria;


public interface SampleService extends RestService{
	
	List<? extends Sample> list();

	List<? extends Sample> list(SampleCriteria criteria);
	
	Sample get(String sampleId);
}
