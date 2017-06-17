package org.openstack4j.openstack.scaling.options;

import com.google.common.collect.Maps;

import java.util.Map;

public class ScalingActivityLogListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();

	private ScalingActivityLogListOptions() {
	}

	public static ScalingActivityLogListOptions create() {
		return new ScalingActivityLogListOptions();
	}

	public ScalingActivityLogListOptions startTime(String startTime) {
		return add("start_time", startTime);
	}

	public ScalingActivityLogListOptions endTime(String endTime) {
		return add("end_time", endTime);
	}

	public ScalingActivityLogListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}

	public ScalingActivityLogListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	public ScalingActivityLogListOptions add(String key, Object value) {
		if (value != null)
			this.queryParam.put(key, value);

		return this;
	}

	public Map<String, Object> getOptions() {
		return this.queryParam;
	}
}
