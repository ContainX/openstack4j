package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * HealthMonitorType
 * @author liujunpeng
 *
 */
public enum HealthMonitorType {
	PING(0), TCP(1), HTTP(2), HTTPS(3);
	
	int code;

	private HealthMonitorType(int code) {
		this.code = code;
	}

	@JsonCreator
	public static HealthMonitorType valueOf(int value) {
		for (HealthMonitorType type : HealthMonitorType.values()) {
			if (type.code() == value) {
				return type;
			}
		}
		return PING;
	}

	@JsonValue
	public int code() {
		return code;
	}
}
