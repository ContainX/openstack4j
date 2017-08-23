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
package com.huawei.openstack4j.openstack.message.queue.domain;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent a Message of the {@link Queue}
 *
 * @author QianBiao.NG
 * @date   2017-07-21 16:37:17
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueueMessage implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * Indicates the message body. (could be any java object that can be serialize as JSON)
	 * 
	   NOTE::
	   
		`\` and `"` is defined as an escape character in a message
		body. If you need to enter a backward slash (\) or a
		double quotation mark (") in a message body, enter
		\\ or \".
		
	 */
	@JsonProperty("body")
	Object body;

	/**
	 * Indicates the list of attributes, including attribute names and values.
	 */
	HashMap<String, Object> attributes;

	public static class QueueMessages extends ListResult<QueueMessage> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("messages")
		private List<QueueMessage> messages = Lists.newArrayList();

		public List<QueueMessage> value() {
			return messages;
		}

		@JsonCreator
		public QueueMessages(List<QueueMessage> messages) {
			this.messages = messages;
		}

	}

}
