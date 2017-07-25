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
package org.openstack4j.openstack.message.queue.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A model represent a Consumer Group of {@link Queue}
 *
 * @author QianBiao.NG
 * @date   2017-07-21 16:36:41
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerGroup implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * identifier of the consumer group
	 */
	@JsonProperty("id")
	String id;

	/**
	 * name of the consumer group
	 */
	@JsonProperty("name")
	String name;

	/**
	 * Indicates the total number of messages 
	 * (not including the messages that have expired and been deleted) in a consumer group.
	 */
	@JsonProperty("produced_messages")
	Integer producedMessages;
	
	/**
	 * Indicates the total number of messages that are successfully consumed
	 */
	@JsonProperty("consumed_messages")
	Integer consumedMessages;
	
	/**
	 * Indicates the number of messages that can be consumed.
	 */
	@JsonProperty("available_messages")
	Integer availableMessages;
	

	public static class ConsumerGroups extends ListResult<ConsumerGroup> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("groups")
		private List<ConsumerGroup> groups = Lists.newArrayList();

		public List<ConsumerGroup> value() {
			return groups;
		}
		
	}

}
