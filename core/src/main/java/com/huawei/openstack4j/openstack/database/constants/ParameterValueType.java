package com.huawei.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Database configuration parameter value type
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum ParameterValueType {

	Integer("integer"), String("string"), Boolean("boolean"), Float("float"), List("list");

	String value;

	ParameterValueType(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static ParameterValueType forValue(String value) {
		if (value != null) {
			for (ParameterValueType state : ParameterValueType.values()) {
				if (value.equals(state.value)) {
					return state;
				}
			}
		}
		return null;
	}
}