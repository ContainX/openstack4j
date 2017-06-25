package org.openstack4j.openstack.cloudeye.internal;

import org.openstack4j.api.cloudeye.MetricService;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeMetric;

import java.util.List;

import static org.openstack4j.core.transport.ClientConstants.PATH_FAVORITE_METRICS;
import static org.openstack4j.core.transport.ClientConstants.PATH_METRICS;


public class CloudEyeMetricServiceImpl extends BaseCloudEyeServices
		implements MetricService {

	@Override
	public List<? extends Metric> getList() {
		return get(CloudEyeMetric.CloudEyeMetrics.class, uri(PATH_METRICS)).execute().getList();
	}
	@Override
	public List<? extends Metric> getList(MetricFilterOptions options) {
		return get(CloudEyeMetric.CloudEyeMetrics.class, uri(PATH_METRICS)).params(options.getOptions()).execute().getList();
	}

	@Override
	public  List<? extends Metric> getFavoriteList() {
		return get(CloudEyeMetric.CloudEyeMetrics.class, uri(PATH_FAVORITE_METRICS)).execute().getList();
	}
}
