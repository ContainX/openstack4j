package org.openstack4j.openstack.scaling.options;

import com.google.common.collect.Maps;

import java.util.Map;

public class ScalingPolicyListOptions {
	private Map<String, Object> queryParam = Maps.newHashMap();
	
	private ScalingPolicyListOptions() { }
	
	public static ScalingPolicyListOptions create() {
		return new ScalingPolicyListOptions();
	}
	
	public ScalingPolicyListOptions policyName(String policyName) {
		return add("scaling_policy_name", policyName);
	}
	
	public ScalingPolicyListOptions policyType(String policyType) {
		return add("scaling_policy_type", policyType);
	}
	
	public ScalingPolicyListOptions startNumber(Integer startNumber) {
		return add("start_number", startNumber);
	}
	
	public ScalingPolicyListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	public ScalingPolicyListOptions add(String key, Object value) {
		if(value != null)
			this.queryParam.put(key, value);
		return this;
	}
	
	public Map<String, Object> getOptions() {
		return this.queryParam;
	}
}
