package org.openstack4j.openstack.key.management.options;

import java.util.Map;

import com.google.common.collect.Maps;

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
