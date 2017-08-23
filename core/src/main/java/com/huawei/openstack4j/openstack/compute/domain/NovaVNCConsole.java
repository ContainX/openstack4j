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
package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.compute.VNCConsole;

import com.google.common.base.MoreObjects;

/**
 * Represents a VNC Console which provides VNC connection information for a remote server
 *
 * @author Jeremy Unruh
 */
@JsonRootName("console")
public class NovaVNCConsole implements VNCConsole {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private Type type;

	@JsonProperty
	private String url;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getURL() {
		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("type", type).add("url", url).toString();
	}

	public static NovaConsole getConsoleForType(Type type) {
	    if (type == Type.SPICE)
	        return new NovaConsoleSPICE(type);

	    return new NovaConsoleVNC(type);
	}

	public static class NovaConsole implements ModelEntity {

        private static final long serialVersionUID = 1L;

        @JsonProperty("type")
        protected String type;

        public NovaConsole(Type type) {
            this.type = type.value();
        }
	}

	@JsonRootName("os-getVNCConsole")
	public static class NovaConsoleVNC extends NovaConsole {

        private static final long serialVersionUID = 1L;

        public NovaConsoleVNC(Type type) {
            super(type);
        }
	}

	@JsonRootName("os-getSPICEConsole")
    public static class NovaConsoleSPICE extends NovaConsole {

        private static final long serialVersionUID = 1L;

        public NovaConsoleSPICE(Type type) {
            super(type);
        }
    }
}
