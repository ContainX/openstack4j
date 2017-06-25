package org.openstack4j.openstack.cloudeye.internal;

import com.google.common.collect.Maps;
import org.openstack4j.api.cloudeye.MetricDataService;
import org.openstack4j.model.cloudeye.*;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.cloudeye.domain.CloudEyeMetricAggregation;
import org.openstack4j.openstack.dns.v2.domain.DesignatePTR;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_METRIC_DATAS;
import static org.openstack4j.core.transport.ClientConstants.PATH_PTR;


public class CloudEyeMetricDataServiceImpl extends BaseCloudEyeServices
		implements MetricDataService {

	@Override
	public MetricAggregation get(String namespace, String metric_name, Date from, Date to, Period period, Filter filter, String[] dimValues) {
		checkNotNull(namespace);
		checkNotNull(metric_name);
		checkNotNull(from);
		checkNotNull(to);
		checkNotNull(period);
		checkNotNull(filter);
		checkNotNull(dimValues);
		Invocation<CloudEyeMetricAggregation> invocation = get(CloudEyeMetricAggregation.class, uri(PATH_METRIC_DATAS));
		Map<String, Object> filters = new HashMap<>();
		filters.put("namespace", namespace);
		filters.put("metric_name", metric_name);
		filters.put("from", from.getTime());
		filters.put("to", to.getTime());
		filters.put("period", period.getCode());
		filters.put("filter", filter.value());
		for (int i = 0; i < dimValues.length; i++) {
			filters.put("dim." + i, dimValues[i]);
		}
		invocation.params(filters);
		return invocation.execute();
	}

	@Override
	public ActionResponse add(List<? extends MetricData> metrics) {
		checkNotNull(metrics);
		return postWithResponse(PATH_METRIC_DATAS).entity(metrics).execute();
	}
}
