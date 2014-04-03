package org.openstack4j.model.compute;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * 
 * Represents an IP Protocol Type
 * 
 * @author Jeremy Unruh
 *
 */
public enum IPProtocol {

	TCP, UDP, ICMP, UNRECOGNIZED;
	
	@JsonCreator
	public static IPProtocol value(String v) 
	{
		if (v == null) return UNRECOGNIZED;
		try {
			return valueOf(v.toUpperCase());
		} catch (IllegalArgumentException e) {
			return UNRECOGNIZED;
		}
	}
	
	@JsonValue
	public String value() {
		return name().toLowerCase();
	}
	
}
