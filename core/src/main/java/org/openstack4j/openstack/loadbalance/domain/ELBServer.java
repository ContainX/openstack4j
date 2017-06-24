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
package org.openstack4j.openstack.loadbalance.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.loadbalance.Server;
import org.openstack4j.openstack.common.DateTimeUtils;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ELBServer implements Server {

	private static final long serialVersionUID = -8385432145897899137L;

	private String id;

	@JsonProperty("server_id")
	private String serverId;

	@JsonProperty("server_address")
	private String serverAddress;

	private String address;

	@JsonProperty("server_name")
	private String serverName;

	private String status;

	@JsonProperty("health_status")
	private String healthStatus;

	private List<IdResourceEntity> listeners;

	@JsonProperty("create_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date createTime;

	@JsonProperty("update_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDHMS)
	private Date updateTime;

	public static class ELBServers extends ListResult<ELBServer> {

		private static final long serialVersionUID = -2100665693687367863L;

		private List<ELBServer> servers;

		@Override
		protected List<ELBServer> value() {
			return servers;
		}
	}
}
