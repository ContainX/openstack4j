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

import org.openstack4j.model.loadbalance.Certificate;

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
public class ELBCertificate implements Certificate {

	private static final long serialVersionUID = 4428629100301469093L;

	private String id;

	private String name;

	private String description;

	private String certificate;

	@JsonProperty("private_key")
	private String privateKey;

	@JsonProperty("create_time")
	private Date createTime;

	@JsonProperty("update_time")
	private Date updateTime;

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Certificates {

		private List<Certificate> certificates;

		@JsonProperty("instance_num")
		private String instanceNum;
	}
}
