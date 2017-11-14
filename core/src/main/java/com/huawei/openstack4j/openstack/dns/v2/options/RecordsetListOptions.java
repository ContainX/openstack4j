package com.huawei.openstack4j.openstack.dns.v2.options;

import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.model.dns.v2.ZoneType;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class RecordsetListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	/**
	 * setup pagination limit filter option
	 * 
	 * @param limit limit value
	 * @return
	 */
	public RecordsetListOptions limit(Integer limit) {
		return add("limit", limit);
	}

	/**
	 * setup pagination marker of last page
	 * 
	 * @param offset offset value
	 * @return
	 */
	public RecordsetListOptions marker(String marker) {
		return add("marker", marker);
	}
	
	public RecordsetListOptions zoneType(ZoneType zoneType) {
		return add("zone_type", zoneType.value());
	}

	private RecordsetListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static RecordsetListOptions create() {
		return new RecordsetListOptions();
	}
	
}
