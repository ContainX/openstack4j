package com.huawei.openstack4j.openstack.message.notification.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PushFailedPolicy {
	
	// keep in a failed queue when failed
	Queue(0), 
	// drop message when failed
	Drop(1);

	Integer value;

	PushFailedPolicy(Integer value) {
		this.value = value;
	}

	@JsonValue
	Integer value() {
		return value;
	}

	@JsonCreator
	public static PushFailedPolicy forValue(Integer value) {
		if (value != null) {
			for (PushFailedPolicy state : PushFailedPolicy.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}