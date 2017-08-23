package com.huawei.openstack4j.openstack.key.management.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum KeyState {
	
	Enabled("2"), Disabled("3"), ScheduledDeletion("4");

	String value;

	KeyState(String value) {
		this.value = value;
	}
	
	@JsonValue
	String value() {
		return value;
	}

	@JsonCreator
	public static KeyState forValue(String value) {
		if (value != null) {
			for (KeyState state : KeyState.values()) {
				if (value.equals(state.value)) {
					return state;
				} 
			}
		}
		return null;
	}
}