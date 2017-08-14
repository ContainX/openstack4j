package org.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Database Instance Parameter Operation Result Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum OperateInstanceParamResult {
	
	SUCCESS("0"), MASTER_SUCCESS_REPLICA_FAIL("1");

	String value;

	OperateInstanceParamResult(String value) {
		this.value = value;
	}
	
	@JsonValue
	String value() {
		return value;
	}

	@JsonCreator
	public static OperateInstanceParamResult forValue(String value) {
		if (value != null) {
			for (OperateInstanceParamResult state : OperateInstanceParamResult.values()) {
				if (value.equals(state.value)) {
					return state;
				} 
			}
		}
		return null;
	}
}