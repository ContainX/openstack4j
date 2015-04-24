package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Protocol type
 * @author liujunpeng
 *
 */
public enum Protocol {
	HTTP(0), TCP(1), HTTPS(2);
	int code;
	
	private Protocol(int code) {
		this.code = code;
	}

	@JsonCreator
	public static Protocol valueOf(int value) {
		for (Protocol protocol : Protocol.values()) {
			if (protocol.code() == value) {
				return protocol;
			}
		}
		return HTTP;
	}

	@JsonValue
	public int code() {
		return code;
	}
}
