package org.openstack4j.model.network;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * IP Address Version Type (V4/V6)
 * 
 * @author Jeremy Unruh
 */
public enum IPVersionType {
	V4(4),
	V6(6)
	;
	private final int version;
	
	private IPVersionType(int version) {
		this.version = version;
	}
	
	/**
	 * Gets the version in Integer form
	 *
	 * @return the version as int
	 */
	@JsonValue
	public int getVersion() {
		return version;
	}

	@JsonCreator
	public static IPVersionType valueOf(int value) {
		for (IPVersionType v : IPVersionType.values()) {
			if (v.version == value) {
				return v;
			}
		}
		return V4;
	}
	
}
