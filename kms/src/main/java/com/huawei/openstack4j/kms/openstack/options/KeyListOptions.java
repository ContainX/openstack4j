package com.huawei.openstack4j.kms.openstack.options;

import java.util.Map;

import com.google.common.collect.Maps;
import com.huawei.openstack4j.kms.openstack.constants.KeyState;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class KeyListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();
	
	public KeyListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	public KeyListOptions marker(String marker) {
		return add("marker", marker);
	}
	
	public KeyListOptions keyState(KeyState enabled) {
		return add("key_state", enabled);
	}
	
	public KeyListOptions sequence(String sequence) {
		return add("sequence", sequence);
	}

	private KeyListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static KeyListOptions create() {
		return new KeyListOptions();
	}

}
