package com.huawei.openstack4j.openstack.cloud.trace.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum TraceStatus {
	
	Normal("normal"), Warning("warning"), Incident("incident");

	String value;

	TraceStatus(String value) {
		this.value = value;
	}
	
	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static TraceStatus forValue(String value) {
		if (value != null) {
			for (TraceStatus state : TraceStatus.values()) {
				if (value.equals(state.value)) {
					return state;
				} 
			}
		}
		return null;
	}
}