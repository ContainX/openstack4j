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
import org.openstack4j.openstack.message.notification.constant.Protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent SMN message template which used for 
 * quickly message sending to reduce the request data volume.
 *
 * @author QianBiao.NG
 * @date   2017-07-18 10:41:47
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MessageTemplate extends TracableRequest implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;
	
	/**
	 * identifier of this message template
	 */
	@JsonProperty("message_template_id")
	String id;
	
	/**
	 * the protocol used for message pushing
	 */
	@JsonProperty("protocol")
	Protocol protocol;

	
	/**
	 * the name of this message template
	 */
	@JsonProperty("message_template_name")
	String name;
	
	/**
	 * the language of this message template
	 */
	@JsonProperty("locale")
	String locale;
	
	/**
	 * the content of this message template
	 */
	@JsonProperty("content")
	String content;
	
	/**
	 * tags of this message template
	 */
	@JsonProperty("tag_names")
	List<String> tags;

	
	/**
	 * Creation time (timestamp) of this message template
	 */
	@JsonProperty("create_time")
	Date createTime;

	/**
	 * Latest update time (timestamp) of this message template
	 */
	@JsonProperty("update_time")
	Date updateTime;


	/**
	 * A modal used to mapping message template list API
	 *
	 * @author QianBiao.NG
	 * @date   2017-07-18 11:08:44
	 */
	public static class MessageTemplates extends ListResult<MessageTemplate> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("message_templates")
		private List<MessageTemplate> templates;

		@JsonProperty("request_id")
		String requestId;

		public List<MessageTemplate> value() {
			return templates;
		}

		public String getRequestId() {
			return requestId;
		}

	}

}
