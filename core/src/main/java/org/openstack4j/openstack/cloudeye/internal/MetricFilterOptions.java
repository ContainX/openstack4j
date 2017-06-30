package org.openstack4j.openstack.cloudeye.internal;

import java.util.HashMap;
import java.util.Map;

import org.openstack4j.model.cloudeye.OrderType;

import com.google.common.collect.Maps;

/**
 * Created by coa.ke on 6/24/17.
 */
public class MetricFilterOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private MetricFilterOptions() {
	}

	public static MetricFilterOptions create() {
		return new MetricFilterOptions();
	}

	public MetricFilterOptions limit(Integer limit) {
		return add("limit", limit);
	}

	public MetricFilterOptions metricName(String metricName) {
		return add("metric_name", metricName);
	}

	/**
	 * @param start The paging start value in the format: namespace.metric_name.key: value
	 * @return
	 */
	public MetricFilterOptions start(String start) {
		return add("start", start);
	}

	public MetricFilterOptions namespace(String namespace) {
		return add("namespace", namespace);
	}

	public MetricFilterOptions order(OrderType orderType) {
		return add("order", orderType.value());
	}

	public MetricFilterOptions dim(String[] dimValues) {
		if (dimValues != null) {
			Map<String, String> dimsMap = new HashMap<>();
			for (int i = 0; i < dimValues.length; i++) {
				dimsMap.put("dim." + i, dimValues[i]);
			}
			return addAll(dimsMap);
		}
		return this;
	}

	private MetricFilterOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	private MetricFilterOptions addAll(Map<String, String> queryValues) {
		if (queryValues != null) {
			this.queryParams.putAll(queryValues);
		}
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}