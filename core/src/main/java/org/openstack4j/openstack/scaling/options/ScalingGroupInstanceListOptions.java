package org.openstack4j.openstack.scaling.options;

import java.util.Map;

import com.google.common.collect.Maps;

public class ScalingGroupInstanceListOptions {
	private Map<String, Object> queryParams = Maps.newHashMap();

	private ScalingGroupInstanceListOptions() {
	}

	public static ScalingGroupInstanceListOptions create() {
		return new ScalingGroupInstanceListOptions();
	}

	public ScalingGroupInstanceListOptions lifeCycleState(String state) {
		return add("life_cycle_state", state);
	}

	public ScalingGroupInstanceListOptions heathStatus(String status) {
		return add("health_status", status);
	}

	public ScalingGroupInstanceListOptions startNumber(Integer number) {
		return add("start_number", number);
	}

	public ScalingGroupInstanceListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	private ScalingGroupInstanceListOptions add(String key, Object value) {
		if (value != null)
			this.queryParams.put(key, value);

		return this;
	}
	
	public Map<String, Object> getOptions() {
		return queryParams;
	}

}
