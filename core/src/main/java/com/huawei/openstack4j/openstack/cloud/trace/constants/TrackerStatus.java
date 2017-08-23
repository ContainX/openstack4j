package com.huawei.openstack4j.openstack.cloud.trace.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum TrackerStatus {
	
	Enabled("enabled"), Disabled("disabled"), Error("error");

	String value;

	TrackerStatus(String value) {
		this.value = value;
	}
	
	@JsonValue
	String value() {
		return value;
	}

	@JsonCreator
	public static TrackerStatus forValue(String value) {
		if (value != null) {
			for (TrackerStatus state : TrackerStatus.values()) {
				if (value.equals(state.value)) {
					return state;
				} 
			}
		}
		return null;
	}
}