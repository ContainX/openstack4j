package org.openstack4j.openstack.scaling.options;

import java.util.Map;

import com.google.common.collect.Maps;

public class ScalingGroupListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private ScalingGroupListOptions() {
	}

	public static ScalingGroupListOptions create() {
		return new ScalingGroupListOptions();
	}

	public ScalingGroupListOptions groupName(String groupName) {
		return add("scaling_group_name", groupName);
	}

	public ScalingGroupListOptions configId(String configId) {
		return add("scaling_configuration_id", configId);
	}

	public ScalingGroupListOptions groupStatus(String groupStatus) {
		return add("scaling_group_status", groupStatus);
	}

	public ScalingGroupListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}

	public ScalingGroupListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	private ScalingGroupListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}
