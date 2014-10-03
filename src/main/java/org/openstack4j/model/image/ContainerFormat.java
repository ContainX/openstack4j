package org.openstack4j.model.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * The container format refers to whether the virtual machine image is in a file format that also
 * contains metadata about the actual virtual machine.
 * 
 * Note that the container format string is not currently used by Glance or other OpenStack
 * components, so it is safe to simply specify {@link #BARE} as the container format if you are
 * unsure.
 * 
 * @author Jeremy Unruh
 */
public enum ContainerFormat {
	/**
	 * This indicates there is no container or metadata envelope for the image
	 */
	BARE,

	/**
	 * This is the OVF container format
	 */
	OVF,

	/**
	 * This indicates what is stored in Glance is an Amazon kernel image
	 */
	AKI,

	/**
	 * This indicates what is stored in Glance is an Amazon ramdisk image
	 */
	ARI,

	/**
	 * This indicates what is stored in Glance is an Amazon machine image
	 */
	AMI,

	/**
	 * Type unknown
	 */
	UNRECOGNIZED;
	
	@JsonValue
	public String value() {
		return name().toLowerCase();
	}
	
	@JsonCreator
	public static ContainerFormat value(String cf) {
		if (cf == null || cf.isEmpty()) return UNRECOGNIZED;
		try
		{
			return valueOf(cf.toUpperCase());
		}
		catch (IllegalArgumentException e) {
			return UNRECOGNIZED;
		}
	}
}
