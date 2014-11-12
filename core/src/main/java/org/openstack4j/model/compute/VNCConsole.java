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
		NOVNC("novnc"), 
		XVPVNC("xvpvnc"),
		SPICE("spice-html5"),
		UNRECOGNIZED("unregonized")
		;
		
		private final String value;
		
		private Type(String value) {
		    this.value = value;
		}
		
		@JsonValue
		public String value() {
			return value;
		}
		
		@JsonCreator
		public static Type value(String vncType) {
			if (vncType == null || vncType.isEmpty()) return UNRECOGNIZED;
			try
			{
				for (Type t : Type.values())
				{
				    if (t.value.equalsIgnoreCase(vncType))
				        return t;
				}
                return UNRECOGNIZED;
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
