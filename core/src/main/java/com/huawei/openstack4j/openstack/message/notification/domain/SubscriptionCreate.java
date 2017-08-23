/*******************************************************************************
 * 	Copyright 2017 HuaWei tld and OTC                                
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
package com.huawei.openstack4j.openstack.message.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * Model for creating a topic subscription
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:35:17
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCreate extends TracableRequest implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;
	
	/**
	 * Topic(urn) of this subscription
	 */
	@JsonIgnore
	@JsonProperty("topic_urn")
	String topicUrn;

	/**
	 * the protocol used for message pushing
	 */
	@JsonProperty("protocol")
	Protocol protocol;


	/**
	 * Message receiving endpoint
	 */
	@JsonProperty("endpoint")
	String endpoint;

	/**
	 * Remark of this subscription
	 */
	@JsonProperty("remark")
	String remark;

}
