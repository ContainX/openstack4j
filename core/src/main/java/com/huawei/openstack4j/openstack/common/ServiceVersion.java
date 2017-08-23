/*******************************************************************************
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
package com.huawei.openstack4j.openstack.common;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent OTC Service Version
 *
 * @author QianBiao.NG
 * @date   2017-07-28 16:22:17
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ServiceVersion implements ModelEntity {

	static final long serialVersionUID = -3477831655673026786L;

	@JsonProperty("id")
	String id;

	@JsonProperty("links")
	List<GenericLink> links;

	@JsonProperty("status")
	ServiceStatus status;

	@JsonProperty("updated")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	Date updated;

	public enum ServiceStatus {

		DEPRECATED, CURRENT, STABLE;

		@JsonCreator
		public static ServiceStatus forValue(String value) {
			if (value != null) {
				for (ServiceStatus state : ServiceStatus.values()) {
					if (value.equalsIgnoreCase(state.name())) {
						return state;
					}
				}
			}
			return null;
		}
	}

	public static class ServiceVersionWrap {

		@JsonProperty("version")
		private ServiceVersion version;

		public ServiceVersion getVersion() {
			return version;
		}

		public void setVersion(ServiceVersion version) {
			this.version = version;
		}
	}

	public static class ServiceVersions extends ListResult<ServiceVersion> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("versions")
		private List<ServiceVersion> versions;

		public List<ServiceVersion> value() {
			return versions;
		}

	}

}
