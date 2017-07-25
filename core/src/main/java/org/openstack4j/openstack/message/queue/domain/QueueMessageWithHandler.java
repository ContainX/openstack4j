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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class QueueMessageWithHandler implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * Indicates the queue message
	 */
	@JsonProperty("message")
	QueueMessage message;
	
	/**
	 * Indicates the queue message handler
	 */
	@JsonProperty("handler")
	String handler;

	

	public static class QueueMessageWithHandlers extends ListResult<QueueMessageWithHandler> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("messages")
		private List<QueueMessageWithHandler> messages = Lists.newArrayList();

		public List<QueueMessageWithHandler> value() {
			return messages;
		}

		@JsonCreator
		public QueueMessageWithHandlers(List<QueueMessageWithHandler> messages) {
			this.messages = messages;
		}
	}

}
