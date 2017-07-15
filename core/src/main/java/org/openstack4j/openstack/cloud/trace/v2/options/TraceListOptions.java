package org.openstack4j.openstack.cloud.trace.v2.options;

import java.util.Date;
import java.util.Map;

import org.openstack4j.openstack.cloud.trace.constants.TraceStatus;

import com.google.common.collect.Maps;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class TraceListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();
	
	protected TraceListOptions() {
		
	}
	
	public TraceListOptions id(String traceId) {
		return add("trace_id", traceId);
	}
	
	public TraceListOptions name(String traceName) {
		return add("trace_name", traceName);
	}
	
	public TraceListOptions status(TraceStatus traceStatus) {
		return add("trace_status", traceStatus.value());
	}
	
	public TraceListOptions serviceType(String serviceType) {
		return add("service_type", serviceType);
	}
	
	public TraceListOptions resourceType(String resourceType) {
		return add("resource_type", resourceType);
	}
	
	public TraceListOptions resourceId(String resourceId) {
		return add("resource_id", resourceId);
	}
	
	public TraceListOptions resourceName(String resourceName) {
		return add("resource_name", resourceName);
	}
	
	public TraceListOptions from(Date from) {
		return add("from", from.getTime());
	}
	
	public TraceListOptions to(Date to) {
		return add("to", to.getTime());
	}
	
	public TraceListOptions user(String user) {
		return add("user", user);
	}
	
	public TraceListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	public TraceListOptions marker(String marker) {
		return add("next", marker);
	}
	
	/**
	 * alternative to {@link #marker(String)}
	 * @param next
	 * @return
	 */
	public TraceListOptions next(String next) {
		return add("next", next);
	}
	
	public TraceListOptions sequence(String sequence) {
		return add("sequence", sequence);
	}

	private TraceListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static TraceListOptions create() {
		return new TraceListOptions();
	}

}
