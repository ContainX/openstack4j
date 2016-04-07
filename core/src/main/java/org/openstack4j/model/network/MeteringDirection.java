package org.openstack4j.model.network;

import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Metering trafic direction(INGRESS/EGRESS)
 * 
 * @author Caio Bergamasco
 */
public enum MeteringDirection {
	INGRESS("ingress"),
	EGRESS("egress")
	;
	private final String direction;
	
	private MeteringDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Gets the direction in String form
	 *
	 * @return the direction as String
	 */
	@JsonValue
	public String getDirection() {
		return direction;
	}

}
