package com.huawei.openstack4j.openstack.message.queue.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * the protocol used to send notification to subscriber
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum ConsumeStatus {

	SUCCESS("success"), FAIL("fail");

	String value;

	ConsumeStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static ConsumeStatus forValue(String value) {
		if (value != null) {
			for (ConsumeStatus state : ConsumeStatus.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}