package org.openstack4j.openstack.scaling.options;

import java.util.Map;

import com.google.common.collect.Maps;

public class ScalingConfigListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private ScalingConfigListOptions() {
	}

	public static ScalingConfigListOptions create() {
		return new ScalingConfigListOptions();
	}
	
	public ScalingConfigListOptions configName(String configName) {
		return add("scaling_configuration_name", configName);
	}
	
	public ScalingConfigListOptions imageId(String imageId) {
		return add("image_id", imageId);
	}
	
	public ScalingConfigListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}
	
	public ScalingConfigListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	private ScalingConfigListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}
}
