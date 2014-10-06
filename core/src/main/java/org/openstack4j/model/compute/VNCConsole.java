package org.openstack4j.model.compute;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a VNC Console which provides VNC connection information for a remote server
 *
 * @author Jeremy Unruh
 */
public interface VNCConsole extends ModelEntity {
	
	/**
	 * The OpenStack VNC Console Type
	 */
	public enum Type {
		NOVNC, XVPVNC, UNRECOGNIZED;
		
		@JsonValue
		public String value() {
			return name().toLowerCase();
		}
		
		@JsonCreator
		public static Type value(String vncType) {
			if (vncType == null || vncType.isEmpty()) return UNRECOGNIZED;
			try
			{
				return valueOf(vncType.toUpperCase());
			}
			catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}
	
	/**
	 * @return the VNC console Type
	 */
	Type getType();
	
	/**
	 * @return the connection URL to the VNC Console
	 */
	String getURL();
	
}
