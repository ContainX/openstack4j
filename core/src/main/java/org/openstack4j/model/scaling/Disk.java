/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disk implements ModelEntity {

	private static final long serialVersionUID = -8180543136177519493L;
	
	public enum VolumeType {
		SSD, SATA, SAS;

		@JsonCreator
		public VolumeType forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (VolumeType type : VolumeType.values()) {
					if (type.name().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}
	
	public enum DiskType {
		DATA, SYS;

		@JsonCreator
		public DiskType forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (DiskType type : DiskType.values()) {
					if (type.name().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}
	
	@JsonProperty
	private Integer size;
	
	@JsonProperty("volume_type")
	private VolumeType volumeType;
	
	@JsonProperty("disk_type")
	private DiskType diskType;
}
