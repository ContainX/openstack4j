package com.huawei.openstack4j.openstack.message.notification.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * the protocol used to send notification to subscriber
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum Protocol {
	
	Default("default"),
	EMAIL("email"),
	SMS("sms"),
	HTTP("http"),
	HTTPS("https");

	String value;

	Protocol(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static Protocol forValue(String value) {
		if (value != null) {
			for (Protocol state : Protocol.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}