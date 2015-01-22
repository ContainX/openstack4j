package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Loadbalance Method
 * @author liujunpeng
 *
 */
public enum LbMethod {
	ROUND_ROBIN(0), LEAST_CONNECTIONS(1), SOURCE_IP(2);
	int code;

	private LbMethod(int code) {
		this.code = code;
	}

	@JsonCreator
	public static LbMethod valueOf(int value) {
		for (LbMethod method : LbMethod.values()) {
			if (method.code() == value) {
				return method;
			}
		}
		return ROUND_ROBIN;
	}

	@JsonValue
	public int code() {
		return code;
	}
}
