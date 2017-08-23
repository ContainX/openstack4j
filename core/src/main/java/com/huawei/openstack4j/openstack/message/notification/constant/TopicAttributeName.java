package com.huawei.openstack4j.openstack.message.notification.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TopicAttributeName {

	AccessPolicy("access_policy"),
	SMSSignId("sms_sign_id"), 
	Introduction("introduction");

	String value;

	TopicAttributeName(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static TopicAttributeName forValue(String value) {
		if (value != null) {
			for (TopicAttributeName state : TopicAttributeName.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}