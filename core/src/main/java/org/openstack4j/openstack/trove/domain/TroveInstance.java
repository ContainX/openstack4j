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
package org.openstack4j.openstack.trove.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of database instance
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("instance")
public class TroveInstance implements ModelEntity {

	private static final long serialVersionUID = -7399474725379713926L;

	private Date created;

	private TroveInstanceFlavor flavor;

	private String hostname;

	private List<String> ip;

	private String id;

	private String name;

	private String status;

	private Date updated;

	private Volume volume;

	private InstanceDatastore datastore;

	public class Volume {

		private String type;
		private Integer size;
		
		public String getType() {
			return type;
		}

		public Integer getSize() {
			return size;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).toString();
		}
	}

	public class InstanceDatastore {

		private String type;

		private String version;

		public String getType() {
			return type;
		}

		public String getVersion() {
			return version;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("InstanceDatastore{");
			sb.append("type='").append(type).append('\'');
			sb.append(", version='").append(version).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}

	public Volume getVolume() {
		return volume;
	}

	public InstanceDatastore getDatastore() {
		return datastore;
	}

	public static class DBInstances extends ListResult<TroveInstance> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("instances")
		private List<TroveInstance> instances;

		@Override
		protected List<TroveInstance> value() {
			return instances;
		}
	}

}
