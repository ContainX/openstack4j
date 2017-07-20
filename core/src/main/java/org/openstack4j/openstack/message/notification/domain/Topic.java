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
package org.openstack4j.openstack.message.notification.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;
import org.openstack4j.openstack.message.notification.constant.PushFailedPolicy;

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
public class Topic extends TracableRequest implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * identifier of the topic
	 */
	@JsonProperty("topic_urn")
	String urn;

	/**
	 * name of the topic
	 */
	@JsonProperty("name")
	String name;

	/**
	 * description of the topic
	 */
	@JsonProperty("display_name")
	String displayName;

	/**
	 * notification push policy 
	 */
	@JsonProperty("push_policy")
	PushFailedPolicy pushPolicy;

	/**
	 * Creation time (timestamp) of the topic
	 */
	@JsonProperty("create_time")
	Date createTime;

	/**
	 * Latest update time (timestamp) of the topic
	 */
	@JsonProperty("update_time")
	Date updateTime;

	public static class Topics extends ListResult<Topic> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("topics")
		private List<Topic> topics;

		@JsonProperty("request_id")
		String requestId;

		public List<Topic> value() {
			return topics;
		}

		public String getRequestId() {
			return requestId;
		}

	}

}
