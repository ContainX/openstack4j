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

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * A model represent a Queue of the MessageQueue Service
 *
 * @author QianBiao.NG
 * @date   2017-07-21 16:36:11
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Queue implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * identifier of the queue
	 */
	@JsonProperty("id")
	String id;

	/**
	 * name of the queue
	 */
	@JsonProperty("name")
	String name;

	/**
	 * description of the queue
	 */
	@JsonProperty("description")
	String description;

	/**
	 * Indicates the retention period (unit: min) of a message in a queue.
	 */
	@JsonProperty("reservation")
	Integer reservation;

	/**
	 * Indicates the maximum message size (unit: byte) that is allowed in queue.
	 */
	@JsonProperty("max_msg_size_byte")
	Integer maxMsgSizeByte;

	/**
	 * Indicates the total number of messages 
	 * (not including the messages that have expired and been deleted) in a queue.
	 */
	@JsonProperty("produced_messages")
	Integer producedMessages;

	/**
	 * Indicates the time when the queue is created
	 */
	@JsonProperty("created")
	Date created;

	public static class Queues extends ListResult<Queue> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("queues")
		private List<Queue> queues;

		public List<Queue> value() {
			return queues;
		}

	}

}
