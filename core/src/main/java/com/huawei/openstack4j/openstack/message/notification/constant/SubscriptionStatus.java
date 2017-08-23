package com.huawei.openstack4j.openstack.message.notification.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Subscription status mapping constants
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum SubscriptionStatus {

	NotConfirmed(0),
	Confirmed(1),
	Canceled(3),
	;

	Integer value;

	SubscriptionStatus(Integer value) {
		this.value = value;
	}

	@JsonValue
	public Integer value() {
		return value;
	}

	@JsonCreator
	public static SubscriptionStatus forValue(Integer value) {
		if (value != null) {
			for (SubscriptionStatus state : SubscriptionStatus.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}