package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * SessionPersistence type
 * @author liujunpeng
 *
 */
public enum SessionPersistenceType {
	APP_COOKIE(0),
    HTTP_COOKIE(1),
    SOURCE_IP(2);
	int code;

	private SessionPersistenceType(int code) {
		this.code = code;
	}

	@JsonCreator
	public static SessionPersistenceType valueOf(int value) {
		for (SessionPersistenceType type : SessionPersistenceType.values()) {
			if (type.code() == value) {
				return type;
			}
		}
		return SOURCE_IP;
	}
	
	@JsonValue
	public int code() {
		return code;
	}
}
