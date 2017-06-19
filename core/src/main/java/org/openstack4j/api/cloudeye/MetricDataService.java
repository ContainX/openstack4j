package org.openstack4j.api.cloudeye;

import org.openstack4j.common.RestService;

public interface MetricDataService extends RestService {
	
	public void getMetricAggregationDatas();
	
	public void addMetricDatas();
	
}
