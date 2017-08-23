/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.model.compute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

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
