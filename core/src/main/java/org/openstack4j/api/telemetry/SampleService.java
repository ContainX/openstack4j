package org.openstack4j.api.telemetry;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.telemetry.Sample;


public interface SampleService extends RestService{
	
	List<? extends Sample> list();
	
	Sample get(String sampleId);
}
